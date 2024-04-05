package com.example.harbor_employee.Eworks.dto.request;

import lombok.Data;

@Data
public class EworksCreateReqDto {
    private String payStatusCode;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;
}
