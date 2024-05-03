package com.example.harbor_salary.salary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MySalaryDetailResponse {
    private String employeeId;
    private LocalDate salaryMonthOfYear;
    private int salaryGross;
    private String name;
    private String codeNum;
    private int baseSalary;
    private String position;
}
