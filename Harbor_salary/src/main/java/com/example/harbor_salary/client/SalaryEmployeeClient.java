package com.example.harbor_salary.client;

import com.example.harbor_salary.client.dto.GetEmployResponse;
import com.example.harbor_salary.client.dto.NameBirthDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "employee-service")
public interface SalaryEmployeeClient {
    @GetMapping("/positionCode")
    GetEmployResponse getPositionCodeByEmployeeId(@RequestHeader("employeeId") String employeeId);

    @GetMapping("/employee/get/object")
    NameBirthDto getNameBirth(@RequestHeader("employeeId") String employeeId);
//    test
}
