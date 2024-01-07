package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.ResultPageDTO;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.vo.JudgeVO;
import icu.chzqz.foj.vo.ResultPageVO;

public interface ResultService {
    /**
     * 根据id获取测试结果
     * @param id
     * @return
     */
    JudgeVO getResult(Long id);

    /**
     * 查询结果列表
     * @param resultPageDTO
     * @return
     */
    PageResult<ResultPageVO> list(ResultPageDTO resultPageDTO);
}
