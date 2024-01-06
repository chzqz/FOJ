package icu.chzqz.foj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationVO {
    String name;
    String email;
    Integer accepted;
    Integer experiment;
    Double passRate;
    String createDate;
    String comment;
}
