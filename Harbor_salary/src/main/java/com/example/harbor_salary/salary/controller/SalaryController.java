package com.example.harbor_salary.salary.controller;

import com.example.harbor_salary.global.common.CommonResponse;
import com.example.harbor_salary.salary.domain.Salary;
import com.example.harbor_salary.salary.dto.response.SeveranceDetailRes;
import com.example.harbor_salary.salary.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    // 급여 제공
    /*@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create/{getEmployeeId}")
    public ResponseEntity<Salary> createSalary(@PathVariable("getEmployeeId") String getEmployeeId) {
        Salary salary = salaryService.createSalary(getEmployeeId);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }*/

    @GetMapping("/create/{employeeId}")
    public Salary createSalary(@PathVariable("employeeId") String employeeId) {
        return salaryService.createSalary(employeeId);
    }

    // 개인 급여 목록 조회
    @GetMapping("/mysalarys")
    public ResponseEntity<CommonResponse> findAllSalarys(@RequestHeader(name = "employeeId") String employeeId, Pageable pageable) {
        return new ResponseEntity<>(new CommonResponse("내 급여 목록 출력", salaryService.findAllSalarys(employeeId, pageable)), HttpStatus.OK);
    }

    @GetMapping("/mysalary/{salaryId}")
    public ResponseEntity<CommonResponse> findMySalary(@PathVariable("salaryId") Long salaryId, @RequestHeader(name = "employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("내 급여 출력", salaryService.findMySalary(employeeId, salaryId)), HttpStatus.OK);
    }

    @GetMapping("/svDetails")
    public ResponseEntity<SeveranceDetailRes> getSeveranceDetail(@RequestHeader(name = "employeeId") String employeeId) {
        SeveranceDetailRes severanceDetailRes = salaryService.severanceDetail(employeeId);
        // 결과가 null이 아니라면 정상적으로 반환
        if (severanceDetailRes != null) {
            return ResponseEntity.ok(severanceDetailRes);
        }
        // 결과가 null이나 잘못된 경우, NOT_FOUND 등 적절한 HTTP 상태 코드로 응답
        return ResponseEntity.notFound().build();
    }
}