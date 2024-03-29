package com.example.harbor_salary.controller;

import com.example.harbor_salary.domain.Salary;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.service.SalaryService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;
    private final SalaryClient salaryClient;

    @Autowired
    public SalaryController(SalaryService salaryService, SalaryClient salaryClient) {
        this.salaryService = salaryService;
        this.salaryClient = salaryClient;
    }

    // 급여 제공
    @GetMapping("/create")
    public Salary createSalary() {
        return salaryService.createSalary();
    }


    // 개인 급여 목록 조회
    @GetMapping("/mysalary")
    public MySalaryRequest findMySalary(Principal principal){
        return salaryService.findMySalary(principal.getName());
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
}