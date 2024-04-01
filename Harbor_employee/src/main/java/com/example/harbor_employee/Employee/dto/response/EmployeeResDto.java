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
    String employeeId;
    String department;
    String position;
    String team;
    String name;
}
