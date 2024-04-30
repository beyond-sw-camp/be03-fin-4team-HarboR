package com.example.harbor_salary.salary.service;

import com.example.harbor_salary.client.dto.GetEmployResponse;
import com.example.harbor_salary.client.dto.GetUsersResponse;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.client.SalaryEmployeeClient;
import com.example.harbor_salary.salary.domain.Salary;
import com.example.harbor_salary.salary.domain.SalaryCode;
import com.example.harbor_salary.client.dto.NameBirthDto;
import com.example.harbor_salary.salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.salary.dto.response.MySalaryDetailResponse;
import com.example.harbor_salary.salary.dto.response.SeveranceDetailRes;
import com.example.harbor_salary.salary.repository.SalaryCodeRepository;
import com.example.harbor_salary.salary.repository.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final SalaryCodeRepository salaryCodeRepository;
    private final SalaryClient salaryClient;
    private final SalaryEmployeeClient salaryEmployeeClient;


    public SalaryService(SalaryRepository salaryRepository, SalaryCodeRepository salaryCodeRepository, SalaryClient salaryClient, SalaryEmployeeClient salaryEmployeeClient) {
        this.salaryRepository = salaryRepository;
        this.salaryCodeRepository = salaryCodeRepository;
        this.salaryClient = salaryClient;
        this.salaryEmployeeClient = salaryEmployeeClient;
    }

    private final double NATIONAL_PENSION_RATE = 0.045; // 국민연금료 비율
    private final double HEALTH_INSURANCE_RATE = 0.03545; // 건강보험료 비율
    private final double LONG_TERM_CARE_INSURANCE_RATE = 0.004591; // 장기요양보험료 비율
    private final double EMPLOYMENT_INSURANCE_RATE = 0.009; // 고용보험료 비율


    //월급 계산
    public int calculateSalary(int baseSalaryInput) {

        int baseSalary = (int) Math.floor(baseSalaryInput / 12);
        int nationalPension = (int) Math.floor(baseSalary * NATIONAL_PENSION_RATE);
        int healthInsurance = (int) Math.floor(baseSalary * HEALTH_INSURANCE_RATE);
        int longTermCareInsurance = (int) Math.floor(baseSalary * LONG_TERM_CARE_INSURANCE_RATE);
        int employmentInsurance = (int) Math.floor(baseSalary * EMPLOYMENT_INSURANCE_RATE);
        // 공제액 합계 계산
        int totalDeductions = nationalPension + healthInsurance + longTermCareInsurance +
                employmentInsurance;
        // 실수령액 계산
        return baseSalary - totalDeductions;
    }

    //급여목록조회

    //급여 생성
    public Salary createSalary(String employeeId) {
        GetEmployResponse getEmployResponse = salaryEmployeeClient.getPositionCodeByEmployeeId(employeeId);
        SalaryCode salaryCodes = salaryCodeRepository.findByCodeNum(getEmployResponse.getResults().get(0).getPositionCode());
        int baseSalary = salaryCodes.getBaseSalary();
        int salaryGross = calculateSalary(baseSalary);
        Salary salary = Salary.builder()
                .employeeId(employeeId)
                .salaryGross(salaryGross)
                .salaryCode(salaryCodes)
                .build();
        return salaryRepository.save(salary);
    }

    public List<MySalaryRequest> findAllSalarys(String employeeId, Pageable pageable) {
        // 현재 로그인한 사원의 급여 정보 조회
        Page<Salary> salarys = salaryRepository.findAllByEmployeeId(employeeId, pageable);
        List<Salary> salaryList = salarys.getContent();
        GetUsersResponse getUsersResponse = salaryClient.getUsers(employeeId);
        List<MySalaryRequest> mySalaryRequest = new ArrayList<>();
        mySalaryRequest = salaryList.stream().map(i -> MySalaryRequest.builder()
                .salaryId(i.getSalaryId())
                .employeeId(i.getEmployeeId())
                .salaryGross(i.getSalaryGross())
                .salaryMonthOfYear(i.getSalaryMonthOfYear())
                .name(getUsersResponse.getResults().get(0).getName())
                .birth(getUsersResponse.getResults().get(0).getBirth())
                .build()
        ).collect(Collectors.toList());
        return mySalaryRequest;
    }

    //상세 급여 조회
    public MySalaryDetailResponse findMySalary(String employeeId, Long salaryId) {
        Salary salary = salaryRepository.findByEmployeeIdAndSalaryId(employeeId, salaryId);
        GetUsersResponse getUsersResponse = salaryClient.getUsers(employeeId);
        return MySalaryDetailResponse.builder()
                .employeeId(salary.getEmployeeId())
                .salaryMonthOfYear(salary.getSalaryMonthOfYear())
                .salaryGross(salary.getSalaryGross())
                .name(getUsersResponse.getResults().get(0).getName())
                .baseSalary(salary.getSalaryCode().getBaseSalary())
                .position(salary.getSalaryCode().getPosition())
                .codeNum(salary.getSalaryCode().getCodeNum())
                .build();
    }

    //퇴직금 계산서비스
//    public List<Salary> getRecentSalariesByEmployeeId(String employeeId) {
//        // SalaryRepository에서 직원의 최근 3개의 급여 정보를 가져옵니다.
//        List<Salary> recentSalaries = salaryRepository.findTop3ByEmployeeIdOrderBySalaryMonthOfYearDesc(employeeId);
//        return recentSalaries;
//    }

    public int severancePay(String employeeId) {
        List<Salary> recentSalaries = salaryRepository.findTop3ByEmployeeIdOrderBySalaryMonthOfYearDesc(employeeId);
        int count = salaryRepository.getCountByEmployeeId(employeeId);

        // 최근 급여 정보가 없거나, 급여를 13번 미만 받은 경우 0을 반환합니다.
        if (recentSalaries == null || recentSalaries.isEmpty() || count < 13) {
            throw new IllegalArgumentException("퇴직금 대상자가 아닙니다");
        }

        int totalSalary = 0;
        for (Salary salary : recentSalaries) {
            // 급여 정보가 null이 아닐 경우에만 총액에 더합니다.
            if (salary != null) {
                totalSalary += salary.getSalaryGross();
            }
        }

        // 평균 급여를 계산합니다.
        int averageSalary = totalSalary / recentSalaries.size();
        return averageSalary;
    }

    public SeveranceDetailRes severanceDetail(String employeeId) {
        int SP = severancePay(employeeId);
        NameBirthDto nameBirthDto = salaryEmployeeClient.getNameBirth(employeeId);
        return SeveranceDetailRes.makeDetailRes(
                employeeId,
                nameBirthDto.getName(),
                nameBirthDto.getBirth(),
                nameBirthDto.getLeavingDate(),
                SP
        );
    }

}

