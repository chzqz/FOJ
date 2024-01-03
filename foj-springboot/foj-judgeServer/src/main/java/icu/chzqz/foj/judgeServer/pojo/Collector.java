package icu.chzqz.foj.judgeServer.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collector implements File{
    public Collector(String name, Integer max) {
        this.name = name;
        this.max = max;
        this.pipe = false;
    }

    /**
     * copyOut 文件名
     */
    String name;

    /**
     * 最大大小限制
     */
    Integer max;

    /**
     * 通过管道收集（默认值为false文件收集）
     */
    Boolean  pipe = false;
}
