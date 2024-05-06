package com.example.harbor_employee.Employee.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeUpdateRequestDto {
    private MultipartFile profileImage = null;
    private String employeeId;
    private String phone;
    private String address;
}
