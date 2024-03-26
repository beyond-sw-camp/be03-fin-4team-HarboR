package com.example.harbor_login.Login.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginSignUpReqDto {
    @NotEmpty(message = "name is essential")
    private String name;
    @NotEmpty(message = "email is essential")
    @Email(message = "email is not valid")
    private String email;
    @NotEmpty(message = "password is essential")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d]{8,20}$|^(?=.*\\d)(?=.*[a-z])(?=.*[!#$%’()*+,./:;?@\\^_`{|}~-])[a-z\\d!#$%’()*+,./:;?@\\^_`{|}~-]{9,20}$|^(?=.*\\d)(?=.*[A-Z])(?=.*[!#$%’()*+,./:;?@\\^_`{|}~-])[A-Z\\d!#$%’()*+,./:;?@\\^_`{|}~-]{9,20}$|^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%’()*+,./:;?@\\^_`{|}~-])[A-Za-z\\d!#$%’()*+,./:;?@\\^_`{|}~-]{9,20}$|^(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%’()*+,./:;?@\\^_`{|}~-])[A-Za-z!#$%’()*+,./:;?@\\^_`{|}~-]{9,20}$"
            , message = "특수문자, 소문자 , 대문자 , 숫자중 3가지를 포함, 8글자 이상 20글자 이하로 비밀번호를 작성해주세요")
    private String password;

    @NotEmpty(message = "birth is essential")
    @Size(min = 6, message = "birth lengh is 6")
    private String birth;

}