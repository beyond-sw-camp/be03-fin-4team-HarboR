package com.example.harbor_salary.controller;

import com.example.harbor_salary.client.GetUsersResponse;
import com.example.harbor_salary.client.SalaryClient;
import com.example.harbor_salary.dto.request.MySalaryRequest;
import com.example.harbor_salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;
    private final SalaryClient salaryClient;

    @Autowired
    public SalaryController(SalaryService salaryService, SalaryClient salaryClient) {
        this.salaryService = salaryService;
        this.salaryClient = salaryClient;
    }

    // 개인 급여 목록 조회
    @GetMapping("/mysalary/{id}")
    public List<MySalaryRequest> findMySalary(@PathVariable String id){
        return salaryService.findMySalary(id);
    }

    @GetMapping("/ping")
    public String ping(){
        return salaryClient.adminPing();
    }
}