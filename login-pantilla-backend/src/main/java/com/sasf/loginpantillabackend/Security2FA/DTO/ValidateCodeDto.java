package com.sasf.loginpantillabackend.Security2FA.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCodeDto {
    private Integer code;
    private String username;
}