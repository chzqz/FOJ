package icu.chzqz.foj.judgeServer.service.impl;

import icu.chzqz.foj.entity.Status;
import icu.chzqz.foj.exception.RequestFailException;
import icu.chzqz.foj.judgeServer.DTO.RunDTO;
import icu.chzqz.foj.judgeServer.pojo.*;
import icu.chzqz.foj.judgeServer.service.JudgeService;
import icu.chzqz.foj.properties.JudgeServerProperty;
import icu.chzqz.foj.judgeServer.VO.RunVO;
import icu.chzqz.foj.properties.MessageProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.ExtendedAccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JudgeServerProperty judgeServerProperty;
    @Autowired
    MessageProperty messageProperty;

    @Override
    public Map filesList() {
        String url = "http://"+judgeServerProperty.getJudgeServer()+"/file";
        Map map = restTemplate.getForObject(url, Map.class);
        return map;
    }

    @Override
    public void deleteFile(String id) {
        String url = "http://"+ judgeServerProperty.getJudgeServer()+"/file/"+id;
        restTemplate.delete(url);
    }

    @Override
    public RunVO run(RunDTO runDTO) throws RequestFailException {
        RunVO runVO = new RunVO();

        // 编译阶段
        Request request = new Request();
        Cmd cmd = new Cmd();
        cmd.setArgs(runDTO.getCompileArgs());
        cmd.setEnv(runDTO.getEnv());
        File[] files = new File[3];
        files[0] = new MemoryFile("");
        files[1] = new Collector("stdout",runDTO.getFileMax());
        files[2] = new Collector("stderr",runDTO.getFileMax());
        cmd.setFiles(files);
        cmd.setCpuLimit(10000000000L);
        cmd.setMemoryLimit(104857600L);
        cmd.setProcLimit(50L);

        Map<String, File> copyIn= new HashMap<>();
        copyIn.put(runDTO.getFileName(), new MemoryFile(runDTO.getCode()));
        cmd.setCopyIn(copyIn);
        cmd.setCopyOut(new String[]{"stdout","stderr"});
        cmd.setCopyOutCached(new String[]{runDTO.getFileOutName()});
        request.setCmd(new Cmd[]{cmd});
        QuestionResult compileRes;
        try {
            ResponseEntity<QuestionResult[]> wsResultResponseEntity = restTemplate.postForEntity("http://"+judgeServerProperty.getJudgeServer()+"/run", request, QuestionResult[].class);
            compileRes = Objects.requireNonNull(wsResultResponseEntity.getBody())[0];
        }catch (Exception e){
            log.info("提交失败: {}",runDTO);
            runVO.setStatus(Status.STATUS_SUBMITTED_FAILED);
            runVO.setErrMessage(messageProperty.submitFailed);
            return runVO;
        }
        // 编译出错
        if(!compileRes.getStatus().equals("Accepted")){
            log.info("{}: {}",compileRes.getStatus(),runDTO);
            runVO.setStatus(Status.STATUS_COMPILE_ERROR);
            runVO.setErrMessage(compileRes.getError());
            return runVO;
        }

        String fileId = compileRes.getFileIds().get(runDTO.getFileOutName());
        //编译成功, 开始测试
        ArrayList<String> results = new ArrayList<>();

        request = new Request();

        cmd = new Cmd();
        cmd.setArgs(runDTO.getRunArgs());
        cmd.setEnv(runDTO.getEnv());
        cmd.setCpuLimit(runDTO.getCpuLimit());
        cmd.setMemoryLimit(runDTO.getMemoryLimit());
        cmd.setProcLimit(runDTO.getProcLimit());
        copyIn = new HashMap<>();
        copyIn.put(runDTO.getFileOutName(),new PreparedFile(fileId));
        cmd.setCopyIn(copyIn);

        Map<String, String> testcases = runDTO.getTestcases();
        if(testcases==null) throw new RequestFailException(messageProperty.argumentError);

        Long maxTime = 0L;
        Long maxMem = 0L;
        for (String key : testcases.keySet()) {
            String value = testcases.get(key);
            cmd.setFiles(new File[]{new MemoryFile(key),new Collector("stdout", runDTO.getFileMax()),new Collector("stderr",runDTO.getFileMax())});
            request.setCmd(new Cmd[]{cmd});
            QuestionResult result;
            try{
                ResponseEntity<QuestionResult[]> wsResultResponseEntity = restTemplate.postForEntity("http://"+judgeServerProperty.getJudgeServer()+"/run", request, QuestionResult[].class);
                result = wsResultResponseEntity.getBody()[0];
            }catch (Exception e){
                log.info("提交案例失败: {}",runDTO);
                runVO.setStatus(Status.STATUS_SUBMITTED_FAILED);
                runVO.setErrMessage(messageProperty.submitFailed);
                runVO.setResult(results);
                deleteFile(fileId);
                return runVO;
            }

            //测试失败
            if(!result.getStatus().equals("Accepted")){
                log.info("{}: {}",compileRes.getStatus(),runDTO);
                if(result.getStatus().equals("Memory Limit Exceeded")){
                    runVO.setStatus(Status.STATUS__CPU_TIME_LIMIT_EXCEEDED);
                    runVO.setErrMessage(messageProperty.cpuTimeExceeded);
                }
                else if(result.getStatus().equals("Time Limit Exceeded")){
                    runVO.setStatus(Status.STATUS__MEMORY_LIMIT_EXCEEDED );
                    runVO.setErrMessage(messageProperty.memoryExceeded);
                }
                else{
                    runVO.setStatus(Status.STATUS__RUNTIME_ERROR);
                    runVO.setErrMessage(compileRes.getError());
                }
                runVO.setResult(results);
                deleteFile(fileId);
                return runVO;
            }

            //测试成功
            maxTime = Math.max(result.getTime(),maxTime);
            maxMem  = Math.max(result.getMemory(),maxMem);
            String stdout = result.getFiles().get("stdout");
            results.add(stdout);


            log.info("结果验证: {} : {}",stdout, value);
            if(value!=null&&!value.equals(stdout)){
                log.info("结果错误: {}",stdout);
                runVO.setStatus(Status.STATUS__WRONG_ANSWER);
                runVO.setErrMessage(messageProperty.wrongAnswer);
                runVO.setResult(results);
                deleteFile(fileId);
                return runVO;
            }
        }
        deleteFile(fileId);

        //全部案例通过
        runVO.setStatus(Status.STATUS_ACCEPTED);
        runVO.setTime(maxTime);
        runVO.setMemory(maxMem);
        runVO.setResult(results);
        return runVO;
    }
}
