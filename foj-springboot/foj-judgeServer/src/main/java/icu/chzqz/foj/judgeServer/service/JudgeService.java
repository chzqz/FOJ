package icu.chzqz.foj.judgeServer.service;

import icu.chzqz.foj.exception.RequestFailException;
import icu.chzqz.foj.judgeServer.DTO.RunDTO;
import icu.chzqz.foj.judgeServer.VO.RunVO;

import java.util.Map;

public interface JudgeService {
    /**
     * 获取judge服务器上的文件列表
     * @return
     */
    Map filesList();

    /**
     * 通过文件id, 删除文件
     * @param id
     */
    void deleteFile(String id);

    /**
     * 编译和运行程序, 案例测试
     * @param runDTO
     * @return
     */
    RunVO run(RunDTO runDTO) throws RequestFailException;

}
