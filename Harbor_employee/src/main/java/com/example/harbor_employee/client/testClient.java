package com.example.harbor_employee.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "testClient", url = "http://localhost:8001")
public interface testClient {

    @GetMapping(value = "/login/detail")
    GetUsersResponse getUsers(@RequestParam String email);
}
