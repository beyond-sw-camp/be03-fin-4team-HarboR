package com.example.harbor_login.Login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequestDto {

    private String employeeId;
    private String tempPassword;
    private String changePw;

}
