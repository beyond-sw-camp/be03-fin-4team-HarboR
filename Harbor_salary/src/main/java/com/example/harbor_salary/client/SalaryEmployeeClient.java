package com.example.harbor_salary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Employee" ,url = "http://localhost:8004")
public interface SalaryEmployeeClient {
    @GetMapping("/employee/admin")
    String employeePing();

    @GetMapping("/employee/{employeeId}/positionCode")
    GetEmployResponse getPositionCodeByEmployeeId(@PathVariable("employeeId") String employeeId);
}
