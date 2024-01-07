package icu.chzqz.foj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    Long id;
    String name;
    String description;
    String tip;
    Long maxTime;
    Long maxMemory;
    Long maxProc;
    Integer[] tags;
    Integer status;
    Integer level;
}
