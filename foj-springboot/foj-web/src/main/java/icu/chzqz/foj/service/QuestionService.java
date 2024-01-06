package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.dto.TestDTO;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.vo.QuestionVO;
import icu.chzqz.foj.vo.QuestionsPageVO;
import icu.chzqz.foj.vo.TestResultVO;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
    /**
     * 分页查询
     * @param questionsPageDTO
     * @return
     */
    PageResult<QuestionsPageVO> questions(QuestionsPageDTO questionsPageDTO) throws AccessDeniedException;

    /**
     * 获取题目信息
     * @param id
     * @return
     */
    QuestionVO getQuestion(Long id);

    /**
     * 查询语言列表
     * @return
     */
    List<String> languages();

    /**
     * 提交并判断代码
     * @param judgeDTO
     * @return
     */
    Long judge(JudgeDTO judgeDTO) throws RequestFailException, IOException;

    /**
     * 运行测试案例
     * @param testDTO
     * @return
     */
    TestResultVO runExample(TestDTO testDTO) throws RequestFailException;
}
