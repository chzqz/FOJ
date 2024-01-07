package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.JudgeDTO;
import icu.chzqz.foj.dto.QuestionDTO;
import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.dto.TestDTO;
import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.entity.exception.SystemErrorException;
import icu.chzqz.foj.judgeServer.VO.TestcaseVO;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.vo.QuestionVO;
import icu.chzqz.foj.vo.QuestionsPageVO;
import icu.chzqz.foj.vo.TestResultVO;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 创建题目
     * @return
     */
    Long createQuestion();

    /**
     * 删除题目
     * @param ids
     */
    void deleteQuestion(List<Long> ids);

    /**
     * 修改题目
     * @param questionDTO
     */
    void modifyQuestion(QuestionDTO questionDTO);

    /**
     * 增添案例
     * @param files
     * @param qid
     * @return
     */
    void addTestcase(MultipartFile[] files, Long qid) throws IOException, SystemErrorException;

    /**
     * 删除测试案例
     * @param id
     */
    void deleteTestcase(Long id);

    /**
     * 查询测试案例
     * @param id
     * @return
     */
    TestcaseVO getTestcase(Long id) throws IOException, RequestFailException;

    /**
     * 根据题目id查询案例
     * @param qid
     * @return
     */
    List<TestcaseVO> list(Long qid) throws RequestFailException, IOException;
}
