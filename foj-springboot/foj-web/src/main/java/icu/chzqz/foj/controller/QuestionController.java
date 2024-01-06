package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.QuestionService;
import icu.chzqz.foj.vo.QuestionVO;
import icu.chzqz.foj.vo.QuestionsPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 题目分页请求
     * @param questionsPageDTO
     * @return
     */
    @GetMapping("/user/questions/list")
    public Result<PageResult<QuestionsPageVO>> questions(QuestionsPageDTO questionsPageDTO) throws AccessDeniedException {
        log.info("题目集分页查询: {}", questionsPageDTO);
        PageResult<QuestionsPageVO> pageResult = questionService.questions(questionsPageDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/user/question/{id}")
    public Result<QuestionVO> getQuestion(@PathVariable Long id){
        log.info("获取题目信息: {}", id);
        QuestionVO question = questionService.getQuestion(id);
        return Result.success(question);
    }

    @GetMapping("/user/language/list")
    public Result<List<String>> languages(){
        log.info("查询语言列表");
        List<String> languages = questionService.languages();
        return Result.success(languages);
    }
    /**
     * 提交代码
     * @param judgeDTO
     * @return
     */
    @PostMapping("/judge")
    public Result<Long> judge(JudgeDTO judgeDTO) throws RequestFailException, IOException {
        log.info("提交代码: {}",judgeDTO);
        Long id = questionService.judge(judgeDTO);
        return Result.success(id);
    }
}
