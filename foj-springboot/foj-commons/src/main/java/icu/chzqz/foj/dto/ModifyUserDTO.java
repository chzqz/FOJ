package icu.chzqz.foj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserDTO {
    Integer id;
    String name;
    String email;
    String password;
    Integer authority;
}
