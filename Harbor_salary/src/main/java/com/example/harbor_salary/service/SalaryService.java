package com.example.harbor_salary.service;

import com.example.harbor_salary.domain.Salary;
import com.example.harbor_salary.domain.SalaryTable;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
    // 임시로 현재 로그인한 사원 ID를 반환하는 코드
    // 실제 구현에서는 인증 정보에서 사원 ID를 추출하여 반환해야 합니다.
    public int getCurrentEmployeeId() {
        return 123; // 예시 사원번호
    }

    public List<MySalaryRequest> findMySalary() {
        int employeeId = getCurrentEmployeeId();

        // 현재 로그인한 사원의 급여 정보 조회
        List<Salary> salaries = salaryRepository.findByEmployeeId(employeeId);
        return salaries.stream().map(salary ->
                MySalaryRequest.builder()
                        .employeeId(salary.getEmployeeId())
                        .salaryMonthOfYear(salary.getSalaryMonthOfYear())
                        .salaryBase(salary.getSalaryBase())
                        .position(salary.getSalaryTable().getPosition())
                        .build()
        ).collect(Collectors.toList());
    }
}
