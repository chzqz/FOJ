package icu.chzqz.foj.judgeServer.DTO;

import icu.chzqz.foj.judgeServer.pojo.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunDTO {
    /**
     * 编译参数
     */
    String[] compileArgs;
    /**
     * 运行参数
     */
    String[] runArgs;
    /**
     * 运行环境
     */
    String[] env;
    /**
     * 文件最大大小
     */
    Integer fileMax;
    /**
     * 运行时间限制
     */
    Long cpuLimit;
    /**
     * 内存限制
     */
    Long memoryLimit;
    /**
     * 线程数量限制
     */
    Long procLimit;
    /**
     * 代码
     */
    String code;
    /**
     * 代码文件名
     */
    String fileName;
    /**
     * 编译文件名
     */
    String fileOutName;
    /**
     * 测试案例
     */
    Map<String,String> testcases;
}
