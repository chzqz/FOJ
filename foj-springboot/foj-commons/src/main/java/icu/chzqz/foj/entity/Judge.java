package icu.chzqz.foj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 判断类, 记录判断信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Judge {

    private Long id;
    private Long qId;
    private String qName;
    private Long uId;
    private String username;
    private LocalDateTime submitTime;
    private Integer status;
    private String errorMessage;
    private Integer time;
    private Integer memory;
    private String code;
    private String language;
}