package com.example.harbor_salary.controller;

import com.example.harbor_salary.client.SalaryEmployeeClient;
import com.example.harbor_salary.config.CustomUserDetails;
import com.example.harbor_salary.domain.Salary;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.dto.response.MySalaryDetailResponse;
import com.example.harbor_salary.global.common.CommonResponse;
import com.example.harbor_salary.service.SalaryService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;
    private final SalaryClient salaryClient;
    private final SalaryEmployeeClient salaryEmployeeClient;

    @Autowired
    public SalaryController(SalaryService salaryService, SalaryClient salaryClient, SalaryEmployeeClient salaryEmployeeClient) {
        this.salaryService = salaryService;
        this.salaryClient = salaryClient;
        this.salaryEmployeeClient = salaryEmployeeClient;
    }

    // 급여 제공
    /*@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create/{getEmployeeId}")
    public ResponseEntity<Salary> createSalary(@PathVariable("getEmployeeId") String getEmployeeId) {
        Salary salary = salaryService.createSalary(getEmployeeId);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }*/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create/{getEmployeeId}")
    public Salary createSalary(@PathVariable("getEmployeeId") String getEmployeeId) {
        System.out.println("getEmployeeId = " + getEmployeeId);
        Salary salary = salaryService.createSalary(getEmployeeId);
        return salary;
    }


    // 개인 급여 목록 조회
    @GetMapping("/mysalarys")
    public ResponseEntity<CommonResponse> findAllSalarys(Pageable pageable, Principal principal) {
        List<MySalaryRequest> allSalarys = salaryService.findAllSalarys(principal.getName(), pageable);
        return new ResponseEntity<>(new CommonResponse<>("성공했슈",allSalarys), HttpStatus.OK);
//        return new ResponseEntity<>(new Comm("요청이 정상적으로 실행되었습니다.", principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/mysalary/{salaryId}")
    public ResponseEntity<CommonResponse> findMySalary(@PathVariable("salaryId") Long salaryId,Principal principal) {
        MySalaryDetailResponse mySalaryDetailResponse = salaryService.findMySalary(principal.getName(), salaryId);
        return new ResponseEntity<>(new CommonResponse<>("성공했슈",mySalaryDetailResponse), HttpStatus.OK);
    }



    @GetMapping("/ping")
    public String ping(){
        try{
            return salaryClient.adminPing();
        } catch(FeignException e){
            System.out.println(e.getMessage());
        }
        return "1";
    }
    //employee 핑퐁 확인
    @GetMapping("/employeeping")
    public String employeePing(){
        try{
            return salaryEmployeeClient.employeePing();
        } catch(FeignException e){
            System.out.println(e.getMessage());
        }
        return "1";
    }
}