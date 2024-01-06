package icu.chzqz.foj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.entity.*;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.judgeServer.DTO.RunDTO;
import icu.chzqz.foj.judgeServer.VO.RunVO;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(questionsPageDTO.getUid()!=null&&(Integer)BaseContextUtil.getBaseContext().get("authority")!=2){
            throw new AccessDeniedException(messageProperty.permissionDenied);
        }
        if(questionsPageDTO.getUid() ==null) questionsPageDTO.setUid((Integer) BaseContextUtil.getBaseContext().get("id"));
        PageHelper.startPage(questionsPageDTO.getPage(),questionsPageDTO.getPageSize());
        List<Question> questionList = questionMapper.list(questionsPageDTO);

        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        List<Question> list = pageInfo.getList();
        ArrayList<QuestionsPageVO> questionsPageVOS = new ArrayList<>();
        for (Question question : list) {
            Long id = question.getId();
            System.out.println(question.toString()+"---------------");
            Double passRate;
            if(question.getSubCount()==0) passRate = 0D;
            else passRate = 1.0*question.getAcCount()/question.getSubCount();

            List<Tag> tags = tagMapper.selectByQId(id);

            List<Integer> statuses = judgeMapper.selectStatus(questionsPageDTO.getUid(), id);
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
                    .id(id)
                    .name(question.getName())
                    .build();
            questionsPageVOS.add(questionsPageVO);
        }
        return new PageResult(pageInfo.getTotal(),questionsPageVOS);
    }

    @Override
    public QuestionVO getQuestion(Long id) {
        QuestionVO questionVO = new QuestionVO();
        Question question = questionMapper.selectById(id);
        BeanUtils.copyProperties(question,questionVO);

        Double passRate;
        if(question.getSubCount().equals(0)) passRate = 0D;
        else passRate = 1.0*question.getAcCount()/question.getSubCount();
        questionVO.setPassRate(passRate);

        List<Testcase> testcases = testcaseMapper.selectByQId(id);
        questionVO.setTestcases(testcases);

        List<Tag> tags = tagMapper.selectByQId(id);
        questionVO.setTags(tags);

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
            Path path = Path.of(defaultProperty.testcasePath + testcase.getInput());
            List<String> strings = Files.readAllLines(path);
            StringBuilder input = new StringBuilder("");
            for (String string : strings) {
                input.append(string);
            }
            path = Path.of(defaultProperty.testcasePath + testcase.getOutput());
            strings = Files.readAllLines(path);
            StringBuilder output = new StringBuilder("");
            for (String string : strings) {
                output.append(string);
            }
            testcases.put(input.toString(),output.toString());
        }

        RunDTO runDTO = new RunDTO(compileArgs,runArgs,env,fileMax,cpuLimit,memoryLimit,procLimit,judgeDTO.getCode(),fileName,fileOutName,testcases);
        RunVO result = judgeController.run(runDTO).getData();
        //将结果添加到数据库
        Judge judge = new Judge(null, question.getId(), question.getName(), (Integer)BaseContextUtil.getBaseContext().get("id"),(String)BaseContextUtil.getBaseContext().get("name"), LocalDateTime.now(),result.getStatus(),result.getErrMessage(),result.getTime(),result.getMemory(), judgeDTO.getCode(), judgeDTO.getLanguage());
        Long id = judgeMapper.insert(judge);

        //更新个人数据
        User user = userMapper.selectById((Integer) BaseContextUtil.getBaseContext().get("id"));
        user.setExperiment(user.getExperiment()+1);
        if(result.getStatus().equals(0)) user.setAccepted(user.getAccepted()+1);
        userMapper.update(user);
        //更新题目数据
        question.setSubCount(question.getSubCount()+1);
        if(result.getStatus().equals(0)) question.setAcCount(question.getAcCount()+1);
        questionMapper.update(question);

        return id;
    }
}
