package com.example.harbor_employee.Employee.dto.response;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class ExcelEmployeeDto {
    private String employeeId;
    private String teamCode;
    private String positionCode;
    private String dutyCode;
    private String statusCode;
    private String genderCode;
    private String bankCode;
    private String socialSecurityNumber;
    private String address;
    private String careerYMD;
    private String joinDate;
    private String accountNumber;
    private String departmentCode;
    private String phone;
    private String email;
    private String name;
}
