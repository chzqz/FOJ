package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.ResultPageDTO;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.ResultService;
import icu.chzqz.foj.vo.JudgeVO;
import icu.chzqz.foj.vo.ResultPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping("/user/judge/{id}")
    public Result<JudgeVO> getResult(@PathVariable Long id){
        log.info("获取测试结果: {}", id);
        JudgeVO judgeVO = resultService.getResult(id);
        return Result.success(judgeVO);
    }

    @GetMapping("/user/judge/list")
    public Result<PageResult<ResultPageVO>> list(ResultPageDTO resultPageDTO){
        log.info("结果列表查询: {}", resultPageDTO);
        PageResult<ResultPageVO> pageResult = resultService.list(resultPageDTO);
        return Result.success(pageResult);
    }

}
