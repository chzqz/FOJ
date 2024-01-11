package icu.chzqz.foj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.QuestionDTO;
import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.dto.TestDTO;
import icu.chzqz.foj.entity.*;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.entity.exception.SystemErrorException;
import icu.chzqz.foj.judgeServer.DTO.RunDTO;
import icu.chzqz.foj.judgeServer.VO.RunVO;
import icu.chzqz.foj.judgeServer.VO.TestcaseVO;
import icu.chzqz.foj.judgeServer.controller.JudgeController;
import icu.chzqz.foj.judgeServer.pojo.Cmd;
import icu.chzqz.foj.mapper.*;
import icu.chzqz.foj.properties.DefaultProperty;
import icu.chzqz.foj.properties.LanguageProperty;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.QuestionService;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.util.CmdUtil;
import icu.chzqz.foj.vo.QuestionVO;
import icu.chzqz.foj.vo.QuestionsPageVO;
import icu.chzqz.foj.vo.TestResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    JudgeMapper judgeMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    TestcaseMapper testcaseMapper;
    @Autowired
    MessageProperty messageProperty;
    @Autowired
    LanguageProperty languageProperty;
    @Autowired
    DefaultProperty defaultProperty;
    @Autowired
    JudgeController judgeController;
    @Autowired
    UserMapper userMapper;

    @Override
    public PageResult<QuestionsPageVO> questions(QuestionsPageDTO questionsPageDTO) throws AccessDeniedException {
        PageHelper.startPage(questionsPageDTO.getPage(),questionsPageDTO.getPageSize());
        Page<Question> page = questionMapper.list(questionsPageDTO);

        List<Question> list = page.getResult();
        ArrayList<QuestionsPageVO> questionsPageVOS = new ArrayList<>();
        for (Question question : list) {
            Long id = question.getId();
            Double passRate;
            if(question.getSubCount()==0) passRate = 0D;
            else passRate = 1.0*question.getAcCount()/question.getSubCount();

            List<Tag> tags = tagMapper.selectByQId(id);
            List<Integer> statuses = null;
            if(questionsPageDTO.getUid()!=null) statuses = judgeMapper.selectStatus(questionsPageDTO.getUid(), id);
            Integer status;
            if(statuses==null||statuses.isEmpty()){
                status = 0;
            }
            else {
                status = -1;
                for (Integer s : statuses) {
                    if(s==0) {
                        status = 1;
                        break;
                    }
                }
            }

            QuestionsPageVO questionsPageVO = QuestionsPageVO.builder()
                    .tags(tags)
                    .status(status)
                    .passRate(passRate)
                    .level(question.getLevel())
                    .id(id)
                    .name(question.getName())
                    .build();
            questionsPageVOS.add(questionsPageVO);
        }
        return new PageResult(page.getTotal(),questionsPageVOS);
    }

    @Override
    public QuestionVO getQuestion(Long id) throws IOException {
        Question question = questionMapper.selectById(id);

        Double passRate;
        if(question.getSubCount().equals(0)) passRate = 0D;
        else passRate = 1.0*question.getAcCount()/question.getSubCount();
        List<Testcase> testcases = testcaseMapper.selectByQIdStatus(id,2);
        for (Testcase testcase : testcases) {
            Path path = Path.of(defaultProperty.testcasePath + testcase.getInput());
            List<String> strings = Files.readAllLines(path);
            StringBuilder input = new StringBuilder("");
            for (String string : strings) {
                input.append(string).append("\n");
            }
            path = Path.of(defaultProperty.testcasePath + testcase.getOutput());
            strings = Files.readAllLines(path);
            StringBuilder output = new StringBuilder("");
            for (String string : strings) {
                output.append(string).append("\n");
            }
            testcase.setInput(input.toString());
            testcase.setOutput(output.toString());
        }
        List<Tag> tags = tagMapper.selectByQId(id);

        QuestionVO questionVO = new QuestionVO(id,question.getName(),question.getDescription(),question.getTip(),question.getMaxTime(),question.getMaxMemory(),question.getMaxProc(),testcases,question.getUid(),passRate,tags,question.getLevel(),question.getStatus());
        return questionVO;
    }

    @Override
    public List<String> languages() {
        return languageProperty.getLanguage();
    }

    @Override
    @Transactional
    public Long judge(JudgeDTO judgeDTO) throws RequestFailException, IOException {
        //获取题目信息
        Question question = questionMapper.selectById(judgeDTO.getQId());

        String[] compileArgs, runArgs, env;
        Integer fileMax;
        Long cpuLimit, memoryLimit,procLimit;
        String fileName, fileOutName;
        Map<String,String> testcases = new HashMap<>();

        String language = judgeDTO.getLanguage();
        compileArgs = CmdUtil.getCompileArgs(language);
        runArgs = CmdUtil.getRunArgs(language);
        env = CmdUtil.getEnv(language);
        fileMax = defaultProperty.fileMax;
        cpuLimit = question.getMaxTime();
        memoryLimit = question.getMaxMemory();
        procLimit = question.getMaxProc();
        fileName = CmdUtil.getFileName(language);
        fileOutName = CmdUtil.getFileOutName(language);
        if(language==null || compileArgs==null || runArgs==null || env==null || fileName==null || fileOutName ==null){
            throw new RequestFailException(messageProperty.unsupportedLanguage);
        }
        //填充测试用例
        List<Testcase> list = testcaseMapper.selectByQId(judgeDTO.getQId());
        for (Testcase testcase : list) {
            if(testcase.getStatus()!=1) continue;
            Path path = Path.of(defaultProperty.testcasePath + testcase.getInput());
            List<String> strings = Files.readAllLines(path);
            StringBuilder input = new StringBuilder("");
            for (String string : strings) {
                input.append(string).append("\n");
            }
            path = Path.of(defaultProperty.testcasePath + testcase.getOutput());
            strings = Files.readAllLines(path);
            StringBuilder output = new StringBuilder("");
            for (String string : strings) {
                output.append(string).append("\n");
            }
            testcases.put(input.toString(),output.toString());
        }

        RunDTO runDTO = new RunDTO(compileArgs,runArgs,env,fileMax,cpuLimit,memoryLimit,procLimit,judgeDTO.getCode(),fileName,fileOutName,testcases);
        RunVO result = judgeController.run(runDTO).getData();
        //将结果添加到数据库
        Judge judge = new Judge(null, question.getId(), question.getName(), (Integer)BaseContextUtil.getBaseContext().get("id"),(String)BaseContextUtil.getBaseContext().get("name"), LocalDateTime.now(),result.getStatus(),result.getErrMessage(),result.getTime(),result.getMemory(), judgeDTO.getCode(), judgeDTO.getLanguage());
        judgeMapper.insert(judge);

        //更新个人数据
        User user = userMapper.selectById((Integer) BaseContextUtil.getBaseContext().get("id"));
        user.setExperiment(user.getExperiment()+1);
        if(result.getStatus().equals(0)) user.setAccepted(user.getAccepted()+1);
        userMapper.update(user);
        //更新题目数据
        question.setSubCount(question.getSubCount()+1);
        if(result.getStatus().equals(0)) question.setAcCount(question.getAcCount()+1);
        questionMapper.update(question);

        return judge.getId();
    }

    @Override
    public TestResultVO runExample(TestDTO testDTO) throws RequestFailException {
        Question question = questionMapper.selectById(testDTO.getQid());

        String[] compileArgs, runArgs, env;
        Integer fileMax;
        Long cpuLimit, memoryLimit,procLimit;
        String fileName, fileOutName;
        Map<String,String> testcases = new LinkedHashMap<>();

        String language = testDTO.getLanguage();
        compileArgs = CmdUtil.getCompileArgs(language);
        runArgs = CmdUtil.getRunArgs(language);
        env = CmdUtil.getEnv(language);
        fileMax = defaultProperty.fileMax;
        cpuLimit = question.getMaxTime();
        memoryLimit = question.getMaxMemory();
        procLimit = question.getMaxProc();
        fileName = CmdUtil.getFileName(language);
        fileOutName = CmdUtil.getFileOutName(language);
        if(language==null || compileArgs==null || runArgs==null || env==null || fileName==null || fileOutName ==null){
            throw new RequestFailException(messageProperty.unsupportedLanguage);
        }
        //填充测试用例
        List<String> list = testDTO.getTestcases();
        for (String testcase : list) {
            testcases.put(testcase,null);
        }

        RunDTO runDTO = new RunDTO(compileArgs,runArgs,env,fileMax,cpuLimit,memoryLimit,procLimit,testDTO.getCode(),fileName,fileOutName,testcases);
        RunVO result = judgeController.run(runDTO).getData();

        TestResultVO testResultVO = new TestResultVO(result.getResult(),result.getErrMessage(),result.getStatus());

        return testResultVO;
    }

    @Override
    public Long createQuestion() {
        Question question = new Question();
        question.setUploadTime(LocalDateTime.now());
        question.setAcCount(0);
        question.setSubCount(0);
        question.setLevel(0);
        question.setMaxTime(defaultProperty.maxTime);
        question.setMaxMemory(defaultProperty.maxMemory);
        question.setMaxProc(defaultProperty.procLimit);
        question.setUid((Integer) BaseContextUtil.getBaseContext().get("id"));
        question.setStatus(0);
        questionMapper.insert(question);
        return question.getId();
    }

    @Override
    @Transactional
    public void deleteQuestion(List<Long> ids) {
        if(ids==null || ids.isEmpty()) return;
        //删除测试结果
        judgeMapper.deleteByQids(ids);
        //删除测试案例
        testcaseMapper.deleteByQids(ids);
        //删除标签关联表
        questionMapper.deleteTagsByQids(ids);
        //删除题目
        questionMapper.deleteByIds(ids);
    }

    @Override
    public void modifyQuestion(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO.getId(),questionDTO.getName(),questionDTO.getDescription(),null,LocalDateTime.now(),null,null,questionDTO.getTip(),questionDTO.getMaxTime(),questionDTO.getMaxMemory(),questionDTO.getMaxProc(),null,questionDTO.getStatus(),questionDTO.getLevel());
        Integer[] tags = questionDTO.getTags();
        questionMapper.deleteTagsByQids(Collections.singletonList(questionDTO.getId()));
        if(tags!=null && tags.length!=0) questionMapper.addTags(questionDTO.getId(),tags);
        questionMapper.update(question);
    }

    @Override
    @Transactional
    public void addTestcase(MultipartFile[] files, Long qid) throws IOException, SystemErrorException {
        HashMap<String, MultipartFile> inputFile = new HashMap<>();
        HashMap<String, MultipartFile> outputFile = new HashMap<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            int index = filename.lastIndexOf(".");
            String name = filename.substring(0, index);
            String extension = filename.substring(index+1);
            if("in".equals(extension)){
                inputFile.put(name,file);
            }
            else if("out".equals(extension)){
                outputFile.put(name,file);
            }
        }

        Set<String> keySet = inputFile.keySet();
        for (String key : keySet) {
            MultipartFile input = inputFile.getOrDefault(key, null);
            MultipartFile output = outputFile.getOrDefault(key, null);
            if(input==null || output==null) {
                continue;
            }
            String inPath = "/"+qid+"/"+input.getOriginalFilename();
            File in = new File(defaultProperty.testcasePath + inPath);
            in.mkdirs();
            in.createNewFile();
            input.transferTo(in);
            String outPath = "/"+qid+"/"+output.getOriginalFilename();
            File out = new File(defaultProperty.testcasePath + outPath);
            out.mkdirs();
            out.createNewFile();
            output.transferTo(out);
            Testcase testcase = new Testcase(null,qid,inPath,outPath,1);
            testcaseMapper.insert(testcase);
        }

    }

    @Override
    public void deleteTestcase(Long id) {
        testcaseMapper.deleteById(id);
    }

    @Override
    public TestcaseVO getTestcase(Long id) throws IOException, RequestFailException {
        Testcase testcase = testcaseMapper.selectById(id);
        if(testcase==null) throw new RequestFailException(messageProperty.dataNotFound);
        Path inputPath = Path.of(defaultProperty.testcasePath + testcase.getInput());
        String filename = inputPath.getFileName().toString();
        String name = filename.substring(0,filename.lastIndexOf("."));
        List<String> lines = Files.readAllLines(inputPath);
        StringBuilder sb = new StringBuilder("");
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        String input = sb.toString();
        lines = Files.readAllLines(Path.of(defaultProperty.testcasePath+testcase.getOutput()));
        sb = new StringBuilder("");
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        String output = sb.toString();
        return new TestcaseVO(testcase.getId(),testcase.getQId(),name,input,output,testcase.getStatus());
    }

    @Override
    public List<TestcaseVO> list(Long qid) throws RequestFailException, IOException {
        List<Testcase> list = testcaseMapper.list(qid);
        List<TestcaseVO> result = new ArrayList<>();
        for (Testcase testcase : list) {
            if(testcase==null) throw new RequestFailException(messageProperty.dataNotFound);
            Path inputPath = Path.of(defaultProperty.testcasePath + testcase.getInput());
            String filename = inputPath.getFileName().toString();
            String name = filename.substring(0,filename.lastIndexOf("."));
            List<String> lines = Files.readAllLines(inputPath);
            StringBuilder sb = new StringBuilder("");
            for (String line : lines) {
                sb.append(line).append("\n");
            }
            String input = sb.toString();
            lines = Files.readAllLines(Path.of(defaultProperty.testcasePath+testcase.getOutput()));
            sb = new StringBuilder("");
            for (String line : lines) {
                sb.append(line).append("\n");
            }
            String output = sb.toString();
            result.add(new TestcaseVO(testcase.getId(),testcase.getQId(),name,input,output,testcase.getStatus()));
        }
        return result;
    }
}
