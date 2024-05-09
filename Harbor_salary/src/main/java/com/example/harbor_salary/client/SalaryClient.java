package com.example.harbor_salary.client;

import com.example.harbor_salary.client.dto.GetUsersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "login-service")
public interface SalaryClient {
    @GetMapping("/account/detail")
    GetUsersResponse getUsers(@RequestHeader("employeeId") String employeeId);
}

