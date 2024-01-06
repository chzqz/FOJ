package icu.chzqz.foj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeVO {
    String username;
    Integer status;
    String errorMessage;
    Long time;
    Long memory;
    String code;
    String language;
    LocalDateTime sub_time;
}
