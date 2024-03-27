package com.example.harbor_salary.service;

import com.example.harbor_salary.repository.SalaryTableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SalaryService {

    private final SalaryTableRepository repository;

    public SalaryService(SalaryTableRepository repository) {
        this.repository = repository;
    }

    private final double NATIONAL_PENSION_RATE = 0.045; // 국민연금료 비율
    private final double HEALTH_INSURANCE_RATE = 0.03545; // 건강보험료 비율
    private final double LONG_TERM_CARE_INSURANCE_RATE = 0.004591; // 장기요양보험료 비율
    private final double EMPLOYMENT_INSURANCE_RATE = 0.009; // 고용보험료 비율
    private final double INCOME_TAX = 32380; // 근로소득세
    private final double LOCAL_INCOME_TAX_RATE = 0.1; // 지방소득세 비율


    //월급 계산

    public double calculateSalary(double baseSalary) {
        double nationalPension = baseSalary * NATIONAL_PENSION_RATE;
        double healthInsurance = baseSalary * HEALTH_INSURANCE_RATE;
        double longTermCareInsurance = baseSalary * LONG_TERM_CARE_INSURANCE_RATE;
        double employmentInsurance = baseSalary * EMPLOYMENT_INSURANCE_RATE;
        double localIncomeTax = INCOME_TAX * LOCAL_INCOME_TAX_RATE;


        // 공제액 합계 계산
        double totalDeductions = nationalPension + healthInsurance + longTermCareInsurance +
                employmentInsurance + INCOME_TAX + localIncomeTax;

        // 실수령액 계산
        return baseSalary - totalDeductions;
    }

    //퇴직금 계산서비스
    public class RetirementService {

        public double calculateRetirement(double averageSalary, int totalWorkingDays) {
            return averageSalary * (totalWorkingDays / 365.0);
        }

    }
}

