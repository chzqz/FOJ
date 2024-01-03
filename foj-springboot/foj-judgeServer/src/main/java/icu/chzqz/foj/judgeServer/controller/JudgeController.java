package icu.chzqz.foj.judgeServer.controller;

import icu.chzqz.foj.exception.RequestFailException;
import icu.chzqz.foj.judgeServer.DTO.RunDTO;
import icu.chzqz.foj.judgeServer.pojo.*;
import icu.chzqz.foj.judgeServer.service.JudgeService;
import icu.chzqz.foj.properties.JudgeServerProperty;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.judgeServer.VO.RunVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/judge")
@Slf4j
public class JudgeController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JudgeServerProperty judgeServerProperty;
    @Autowired
    JudgeService judgeService;

    @PostMapping("/test1")
    public QuestionResult[] test(){
        Request request = new Request();
        Cmd cmd = new Cmd();
        String[] args = new String[]{"/usr/bin/g++", "a.cc", "-o", "a"};
        cmd.setArgs(args);
        cmd.setEnv(new String[]{"PATH=/usr/bin:/bin"});
        File[] files = new File[3];
        files[0] = new MemoryFile("");
        files[1] = new Collector("stdout",10240);
        files[2] = new Collector("stderr",10240);
        cmd.setFiles(files);
        cmd.setCpuLimit(10000000000L);
        cmd.setMemoryLimit(104857600L);
        cmd.setProcLimit(50L);

        Map<String, File> copyIn= new HashMap<>();
        copyIn.put("a.cc",new MemoryFile("#include <iostream>\nusing namespace std;\nint main() {\nint a, b;\ncin >> a >> b;\ncout << a + b << endl;\n}"));
        cmd.setCopyIn(copyIn);
        cmd.setCopyOut(new String[]{"stdout","stderr"});
        cmd.setCopyOutCached(new String[]{"a"});


        request.setCmd(new Cmd[]{cmd});
        ResponseEntity<QuestionResult[]> wsResultResponseEntity = restTemplate.postForEntity("http://192.168.1.129:5050/run", request, QuestionResult[].class);
        return wsResultResponseEntity.getBody();
    }


    @PostMapping("/test2")
    public QuestionResult[] test(@RequestBody String id){
        Request request = new Request();

        Cmd cmd1 = new Cmd();
        cmd1.setArgs(new String[]{"a"});
        cmd1.setEnv(new String[]{"PATH=/usr/bin:/bin"});
        cmd1.setFiles(new File[]{new MemoryFile("1 1"),new Collector("stdout",10240),new Collector("stderr",10240)});
        cmd1.setCpuLimit(10000000000L);
        cmd1.setMemoryLimit(104857600L);
        cmd1.setProcLimit(50L);

        HashMap<String, File> copyIn1 = new HashMap<>();
        log.info("测试文件id: {}",id);
        copyIn1.put("a",new PreparedFile(id));
        cmd1.setCopyIn(copyIn1);

        request.setCmd(new Cmd[]{cmd1});
        ResponseEntity<QuestionResult[]> wsResultResponseEntity = restTemplate.postForEntity("http://192.168.1.129:5050/run", request, QuestionResult[].class);
        return wsResultResponseEntity.getBody();
    }

    /**
     * 获取judge服务器上的文件列表
     * @return
     */
    @GetMapping("/files")
    public Result<Map<String,String>> filesList(){
        log.info("judge文件列表");
        Map map = judgeService.filesList();
        return Result.success(map);
    }

    /**
     * 通过文件id, 删除文件
     * @param id 文件id
     * @return
     */
    @DeleteMapping("/file/{id}")
    public Result deleteFile(@PathVariable String id){
        log.info("judge删除文件: {}",id);
        judgeService.deleteFile(id);
        return Result.success();
    }

    /**
     * 提交代码和测试案例进行测试
     * @param runDTO
     * @return
     */
    @PostMapping("/run")
    public Result<RunVO> run(@RequestBody RunDTO runDTO) throws RequestFailException {
        log.info("代码测试: {}",runDTO);
        RunVO runVO = judgeService.run(runDTO);
        return Result.success(runVO);
    }

}
