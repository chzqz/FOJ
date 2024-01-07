package icu.chzqz.foj.judgeServer.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestcaseVO {
    Long id;
    Long qid;
    String name;
    String in;
    String out;
    Integer status;
}
