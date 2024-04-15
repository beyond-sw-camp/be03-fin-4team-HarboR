package com.example.harbor_salary.salary.dto.response;

import com.example.harbor_salary.salary.domain.SalaryCode;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MySalaryDetailResponse {
    private String employeeId;
    private LocalDate salaryMonthOfYear;
    private int salaryGross;
    private String name;
    private SalaryCode salaryCode;



}
