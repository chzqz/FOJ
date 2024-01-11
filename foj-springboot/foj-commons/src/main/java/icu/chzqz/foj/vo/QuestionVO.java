package icu.chzqz.foj.vo;

import icu.chzqz.foj.entity.Tag;
import icu.chzqz.foj.entity.Testcase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionVO {
    Long id;
    String name;
    String description;
    String tip;
    Long maxTime;
    Long maxMemory;
    Long maxProc;
    List<Testcase> testcases;
    Integer uid;
    Double passRate;
    List<Tag> tags;
    Integer level;
    Integer status;

}
