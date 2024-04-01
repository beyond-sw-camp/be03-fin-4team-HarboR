package com.example.harbor_salary.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Login")
public interface SalaryClient {

    @GetMapping("/login/admin")
    String adminPing();

    @GetMapping(value = "/login/account/detail/{id}")
    GetUsersResponse getUsers(@PathVariable("id") String id);
}
