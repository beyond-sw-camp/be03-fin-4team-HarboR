package com.example.harbor_salary.service;

import com.example.harbor_salary.client.GetUsersResponse;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.domain.Salary;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final SalaryClient salaryClient;

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

    //테스트 사원번호
    public int getCurrentEmployeeId() {
        return 123; // 예시 사원번호
    }

    //급여목록조회
    public List<MySalaryRequest> findMySalary(String employeeId) {
        // 현재 로그인한 사원의 급여 정보 조회
        List<Salary> salaries = salaryRepository.findByEmployeeId(employeeId);
        log.info("1");
        GetUsersResponse getUsersResponse = salaryClient.getUsers(employeeId);
        log.info(getUsersResponse.getResults().get(0).getBirth());
        return salaries.stream().map(salary ->
                MySalaryRequest.builder()
                        .employeeId(salary.getEmployeeId())
                        .salaryMonthOfYear(salary.getSalaryMonthOfYear())
                        .salaryBase(salary.getSalaryGross())
                        .birth(getUsersResponse.getResults().get(0).getBirth())
                        .name(getUsersResponse.getResults().get(0).getName())
                        .build()
        ).collect(Collectors.toList());
    }
}

