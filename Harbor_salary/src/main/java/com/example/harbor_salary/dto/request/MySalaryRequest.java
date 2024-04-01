package com.example.harbor_salary.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MySalaryRequest {
    private String employeeId;
    private LocalDate salaryMonthOfYear;
    private int salaryBase;
    private String name;
    private String birth;
}
