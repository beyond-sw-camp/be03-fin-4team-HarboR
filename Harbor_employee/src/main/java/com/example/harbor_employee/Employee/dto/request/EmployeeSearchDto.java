package com.example.harbor_employee.Employee.dto.request;

import lombok.Data;

@Data
public class EmployeeSearchDto {
    // 사원 번호
    private String employeeId;
    // 이름
    private String name;
    // 부서
    private String departmentCode;
    // 소속팀
    private String teamCode;
    // 객체
    private String employee_id;
}
