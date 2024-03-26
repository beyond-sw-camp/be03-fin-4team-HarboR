package com.example.harbor_login.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "testClient", url = "http://localhost:8004")
public interface testClient {

//    @GetMapping(value = "/api/")
//    GetUsersResponse getUsers(@RequestParam("nat") String nation);
}
