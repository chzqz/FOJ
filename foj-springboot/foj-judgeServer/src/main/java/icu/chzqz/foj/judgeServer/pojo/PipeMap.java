package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeMap {
    PipeIndex in;
    PipeIndex out;
    Boolean proxy;
    String name;
    Integer max;
}
