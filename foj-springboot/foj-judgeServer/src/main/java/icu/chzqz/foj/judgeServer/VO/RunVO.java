package icu.chzqz.foj.judgeServer.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunVO {
    /**
     * 结果状态码
     */
    Integer status;
    /**
     * 错误信息
     */
    String errMessage;
    /**
     * 运行时间
     */
    Long time;
    /**
     * 所耗内存
     */
    Long memory;
    /**
     * 测试结果
     */
    List<String> result;
}
