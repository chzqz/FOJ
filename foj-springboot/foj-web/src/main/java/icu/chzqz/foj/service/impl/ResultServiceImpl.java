package icu.chzqz.foj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import icu.chzqz.foj.dto.ResultPageDTO;
import icu.chzqz.foj.entity.Judge;
import icu.chzqz.foj.mapper.JudgeMapper;
import icu.chzqz.foj.result.PageResult;
import icu.chzqz.foj.service.ResultService;
import icu.chzqz.foj.vo.JudgeVO;
import icu.chzqz.foj.vo.ResultPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    JudgeMapper judgeMapper;
    @Override
    public JudgeVO getResult(Long id) {
        Judge judge = judgeMapper.selectById(id);
        return new JudgeVO(judge.getUName(),judge.getStatus(),judge.getErrorMessage(),judge.getTime(),judge.getMemory(),judge.getCode(),judge.getLanguage(),judge.getSubmitTime());
    }

    @Override
    public PageResult<ResultPageVO> list(ResultPageDTO resultPageDTO) {
        PageHelper.startPage(resultPageDTO.getPage(),resultPageDTO.getPageSize());
        Page<Judge> page = judgeMapper.list(resultPageDTO);
        List<Judge> list = page.getResult();
        List<ResultPageVO> results = new ArrayList<>();
        for (Judge judge : list) {
            ResultPageVO resultPageVO = new ResultPageVO(judge.getUId(),judge.getUName(),judge.getQId(),judge.getQName(),judge.getStatus(),judge.getTime(),judge.getMemory(),judge.getLanguage(),judge.getSubmitTime());
            results.add(resultPageVO);
        }
        return new PageResult<ResultPageVO>(page.getTotal(),results);
    }
}
