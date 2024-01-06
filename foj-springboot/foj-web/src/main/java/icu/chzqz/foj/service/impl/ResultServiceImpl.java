package icu.chzqz.foj.service.impl;

import icu.chzqz.foj.entity.Judge;
import icu.chzqz.foj.mapper.ResultMapper;
import icu.chzqz.foj.service.ResultService;
import icu.chzqz.foj.vo.JudgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    ResultMapper resultMapper;
    @Override
    public JudgeVO getResult(Long id) {
        Judge judge = resultMapper.selectById(id);
        return new JudgeVO(judge.getUName(),judge.getStatus(),judge.getErrorMessage(),judge.getTime(),judge.getMemory(),judge.getCode(),judge.getLanguage(),judge.getSubmitTime());
    }
}
