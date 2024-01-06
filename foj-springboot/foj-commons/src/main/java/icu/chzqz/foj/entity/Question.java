package icu.chzqz.foj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime uploadTime;
    private LocalDateTime modifyTime;
    private Integer acCount;
    private Integer subCount;
    private String tip;
    private Long maxTime;
    private Long maxMemory;
    private Long maxProc;
    private Integer uid;
    private Integer status;
    private Integer level;
}