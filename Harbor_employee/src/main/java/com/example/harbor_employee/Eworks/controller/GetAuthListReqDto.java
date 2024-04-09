package com.example.harbor_employee.Eworks.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.Get;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthListReqDto {
    private String employeeId;
    private String departmentCode;

    public GetAuthListReqDto create(String employeeId, String departmentCode){
        return GetAuthListReqDto.builder()
                .employeeId(employeeId)
                .departmentCode(departmentCode)
                .build();
    }
}
