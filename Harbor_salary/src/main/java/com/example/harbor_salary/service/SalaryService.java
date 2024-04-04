package com.example.harbor_salary.service;

import com.example.harbor_salary.client.GetEmployResponse;
import com.example.harbor_salary.client.GetUsersResponse;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.client.SalaryEmployeeClient;
import com.example.harbor_salary.domain.Salary;
import com.example.harbor_salary.domain.SalaryCode;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.dto.response.MySalaryDetailResponse;
import com.example.harbor_salary.repository.SalaryCodeRepository;
import com.example.harbor_salary.repository.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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



    //퇴직금 계산서비스
    public List<Salary> getRecentSalariesByEmployeeId(String employeeId) {
        // SalaryRepository에서 직원의 최근 3개의 급여 정보를 가져옵니다.
        List<Salary> recentSalaries = salaryRepository.findTop3ByEmployeeIdOrderBySalaryMonthOfYearDesc(employeeId);
        return recentSalaries;
    }


    public int severancepay(String employeeId) {
        // 직원의 최근 3개월치 월급 정보를 가져옵니다.
        List<Salary> recentSalaries = getRecentSalariesByEmployeeId(employeeId);

        // 최근 3개월치 월급의 총합 계산
        int totalSalary = 0;
        for (Salary salary : recentSalaries) {
            totalSalary += salary.getSalaryGross();
        }

        // 평균 월급 계산
        int averageSalary = totalSalary / recentSalaries.size();

        // 퇴직금으로 반환 (평균 월급을 사용)
        return averageSalary;
    }
    //급여 생성
    public Salary createSalary(String employeeId){
        GetEmployResponse getEmployResponse = salaryEmployeeClient.getPositionCodeByEmployeeId(employeeId);
        log.info("salaryCodes = " + getEmployResponse.getResults().get(0).getPositionCode());
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
    //급여목록조회
    public List<MySalaryRequest> findAllSalarys(String employeeId, Pageable pageable) {
        // 현재 로그인한 사원의 급여 정보 조회
        Page<Salary> salarys = salaryRepository.findAllByEmployeeId(employeeId,pageable);
        List<Salary> salaryList = salarys.getContent();
        GetUsersResponse getUsersResponse = salaryClient.getUsers(employeeId);
        List<MySalaryRequest> mySalaryRequest = new ArrayList<>();
        mySalaryRequest = salaryList.stream().map(i -> MySalaryRequest.builder()
                .employeeId(i.getEmployeeId())
                .salaryGross(i.getSalaryGross())
                .salaryMonthOfYear(i.getSalaryMonthOfYear())
                .name(getUsersResponse.getResults().get(0).getName())
                .birth(getUsersResponse.getResults().get(0).getBirth())
                .build()
        ).collect(Collectors.toList());
        log.info("생년월일" + getUsersResponse.getResults().get(0).getBirth());
        return mySalaryRequest;
    }
    //상세 급여 목록 조회
    public MySalaryDetailResponse findMySalary(String employeeId, Long salaryId) {
        System.out.println("employeeId = " + employeeId);
        System.out.println("salaryId = " + salaryId);
        Salary salary = salaryRepository.findByEmployeeIdAndSalaryId(employeeId,salaryId);
        GetUsersResponse getUsersResponse = salaryClient.getUsers(employeeId);
        return MySalaryDetailResponse.builder()
                .employeeId(salary.getEmployeeId())
                .salaryMonthOfYear(salary.getSalaryMonthOfYear())
                .salaryGross(salary.getSalaryGross())
                .salaryCode(salary.getSalaryCode())
                .birth(getUsersResponse.getResults().get(0).getBirth())
                .name(getUsersResponse.getResults().get(0).getName())
                .build();
    }
}

