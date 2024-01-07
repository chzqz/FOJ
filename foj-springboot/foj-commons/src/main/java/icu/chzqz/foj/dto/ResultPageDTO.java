package icu.chzqz.foj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPageDTO {
    Integer uid;
    Long qid;
    Integer type;
    String qName;
    String language;
    Integer page;
    Integer pageSize;
}
