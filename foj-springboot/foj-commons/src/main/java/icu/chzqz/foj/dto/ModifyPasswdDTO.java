package icu.chzqz.foj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswdDTO {
    String oldPassword;
    String newPassword;
}
