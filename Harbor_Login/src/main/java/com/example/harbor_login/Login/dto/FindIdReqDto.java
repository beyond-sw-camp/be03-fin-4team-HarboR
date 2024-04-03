package com.example.harbor_login.Login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindReqDto {

    private String email;
    private String employeeId;
    private String name;
    private String password;
}
