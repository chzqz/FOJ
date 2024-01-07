package icu.chzqz.foj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPageVO {
    Integer uid;
    String uName;
    Long qid;
    String qName;
    Integer status;
    Long time;
    Long memory;
    String language;
    LocalDateTime submitTime;
}
