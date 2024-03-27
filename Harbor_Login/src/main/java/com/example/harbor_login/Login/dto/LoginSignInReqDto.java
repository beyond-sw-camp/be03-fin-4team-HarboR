package com.example.harbor_login.Login.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginSignInReqDto {
    @NotEmpty(message = "email is essential")
    @Email(message = "email is not valid")
    private String email;
    @NotEmpty(message = "password is essential")
    @Size(min = 4, message = "minimum length is 4")
    private String password;

    @NotEmpty(message = "employeeId is essential")
    @Size(min = 6, message = "minimum length is 8")
    private String employeeId;
}
