package com.example.harbor_login.Login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPasswordReq {
    private String employeeId;
    private String name;
}
