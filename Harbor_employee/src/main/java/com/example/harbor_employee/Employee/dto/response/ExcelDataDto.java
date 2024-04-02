package com.example.harbor_employee.Employee.dto.response;

import lombok.Data;

@Data
public class ExcelDataDto {
    private String employeeId;
    private String beforeDepartmentCode;
    private String afterDepartmentCode;
    private String positionCode;
    private String issueDate;
    private String updateDutyCode;
}
