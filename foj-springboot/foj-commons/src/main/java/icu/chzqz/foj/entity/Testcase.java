package icu.chzqz.foj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testcase {
    private Long id;
    private Long qId;
    private String input;
    private String output;
    private Integer status;
}