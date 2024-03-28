package com.example.harbor_salary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Login", url = "http://localhost:8001")
public interface SalaryClient {

    @GetMapping("/login/admin")
    String adminPing();

    @GetMapping(value = "/login/account/detail/{id}")
    GetUsersResponse getUsers(@PathVariable String id);
}
