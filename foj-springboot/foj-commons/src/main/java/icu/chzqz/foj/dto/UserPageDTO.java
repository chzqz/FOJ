package icu.chzqz.foj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDTO {
    Integer authority;
    Integer id;
    String name;
    String email;
    Integer page;
    Integer pageSize;
}
