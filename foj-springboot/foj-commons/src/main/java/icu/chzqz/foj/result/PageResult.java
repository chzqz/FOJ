package icu.chzqz.foj.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * 分页查询结果类
 * @param <T> 数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 结果总数
     */
    Long total;
    /**
     * 结果数据
     */
    List<T> data;
}
