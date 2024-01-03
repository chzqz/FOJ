package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件内容类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryFile implements File{
    /**
     * 文件内容
     */
    String content;
}
