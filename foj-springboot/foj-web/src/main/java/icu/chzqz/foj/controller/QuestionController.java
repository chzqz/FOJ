package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.*;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.entity.exception.SystemErrorException;
import icu.chzqz.foj.judgeServer.VO.TestcaseVO;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.QuestionService;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.vo.QuestionVO;
import icu.chzqz.foj.vo.QuestionsPageVO;
import icu.chzqz.foj.vo.TestResultVO;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Result<QuestionVO> getQuestion(@PathVariable Long id) throws IOException {
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
    @PostMapping("/user/judge")
    public Result<Long> judge(@RequestBody JudgeDTO judgeDTO) throws RequestFailException, IOException {
        log.info("提交代码: {}",judgeDTO);
        Long id = questionService.judge(judgeDTO);
        return Result.success(id);
    }

    /**
     * 运行测试案例
     * @return
     */
    @PostMapping("/user/test")
    public Result<TestResultVO> runExample(@RequestBody TestDTO testDTO) throws RequestFailException {
        log.info("运行测试案例: {}", testDTO);
        TestResultVO list = questionService.runExample(testDTO);
        return Result.success(list);
    }

    /**
     * 创建题目
     * @return
     */
    @PostMapping("/setter/questions")
    public Result<Long> createQuestion(){
        log.info("创建题目, 用户: {}", BaseContextUtil.getBaseContext().get("id"));
        Long id = questionService.createQuestion();
        return Result.success(id);
    }

    /**
     * 删除题目
     * @param ids
     * @return
     */
    @DeleteMapping("/setter/questions")
    public Result deleteQuestion( @RequestBody List<Long> ids){
        log.info("删除题目: {}",ids);
        questionService.deleteQuestion(ids);
        return Result.success();
    }

    /**
     * 修改题目
     * @param questionDTO
     * @return
     */
    @PutMapping("/setter/questions")
    public Result modifyQuestion(@RequestBody QuestionDTO questionDTO){
        log.info("修改题目信息: {}", questionDTO);
        questionService.modifyQuestion(questionDTO);
        return Result.success();
    }

    @PostMapping("setter/testcases")
    public Result addTestcase(@RequestParam MultipartFile[] files, @RequestParam Long qid) throws IOException, SystemErrorException {
        log.info("增添案例: {}",qid);
        questionService.addTestcase(files, qid);
        return Result.success();
    }

    @DeleteMapping("/setter/testcases")
    public Result deleteTestcase(@RequestBody Long id){
        log.info("删除测试案例: {}", id);
        questionService.deleteTestcase(id);
        return Result.success();
    }

    @GetMapping("/setter/testcases/{id}")
    public Result<TestcaseVO> getTestcase(@PathVariable Long id) throws IOException, RequestFailException {
        log.info("获取测试案例: {}", id);
        TestcaseVO testcaseVO = questionService.getTestcase(id);
        return Result.success(testcaseVO);
    }

    @GetMapping("setter/testcases/qid/{qid}")
    public Result<List<TestcaseVO>> list(@PathVariable Long qid) throws RequestFailException, IOException {
        log.info("根据题目id查询案例: {}", qid);
        List<TestcaseVO> list = questionService.list(qid);
        return Result.success(list);
    }


}
