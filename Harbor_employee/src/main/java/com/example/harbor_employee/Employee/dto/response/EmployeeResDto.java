package com.example.harbor_employee.Employee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResDto {
    private String employeeId;
    private String department;
    private String position;
    private String team;
    private String name;
}
