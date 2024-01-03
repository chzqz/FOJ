package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResult {
    String status;
    /**
     * 详细错误信息
     */
    String error;
    /**
     * 程序返回值
     */
    Integer exitStatus;
    /**
     * 程序运行 CPU 时间，单位纳秒
     */
    Long time;
    /**
     * 程序运行内存，单位 byte
     */
    Long memory;
    /**
     * 程序运行现实时间，单位纳秒
     */
    Long runTime;
    /**
     * copyOut 和 pipeCollector 指定的文件内容
     */
    Map<String,String> files;
    /**
     * copyFileCached 指定的文件 id
     */
    Map<String,String> fileIds;
    /**
     * 文件错误详细信息
     */
    FileError[] fileError;
}
