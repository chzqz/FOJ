package icu.chzqz.foj.judgeServer.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparedFile implements File{
    /**
     * 文件id
     */
    String fileId;
}
