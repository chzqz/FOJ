package icu.chzqz.foj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageVO {
    Integer id;
    String name;
    String email;
    Integer accepted;
    Integer experiment;
    LocalDateTime  createDate;
    LocalDateTime lastLoginTime;
    Integer authority;
}
