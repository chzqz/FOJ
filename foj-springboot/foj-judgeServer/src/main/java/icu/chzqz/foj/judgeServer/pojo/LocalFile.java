package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本地文件类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalFile implements File{
    /**
     * 文件绝对路径
     */
    String src;
}
