package icu.chzqz.foj.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private String password;
    private Integer accepted;
    private Integer experiment;
    private LocalDateTime createDate;
    private LocalDateTime lastLoginTime;
    private Integer authority;
    private String comment;
}
