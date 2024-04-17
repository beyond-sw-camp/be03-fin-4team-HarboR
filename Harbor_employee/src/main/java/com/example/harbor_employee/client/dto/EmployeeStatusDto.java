package com.example.harbor_employee.client.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStatusDto {
    private String employeeId;
    private String statusCode;
}
