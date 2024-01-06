package icu.chzqz.foj.service;

import icu.chzqz.foj.vo.JudgeVO;

public interface ResultService {
    /**
     * 根据id获取测试结果
     * @param id
     * @return
     */
    JudgeVO getResult(Long id);
}
