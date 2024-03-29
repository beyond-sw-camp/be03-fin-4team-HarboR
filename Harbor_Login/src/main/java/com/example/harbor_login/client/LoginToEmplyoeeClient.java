package com.example.harbor_login.client;


import com.example.harbor_login.Login.dto.LoginMemberResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Emplyoee", url = "http://localhost:8004")
public interface LoginToEmplyoeeClient {

//    @GetMapping(value = "/api/")
//    GetUsersResponse getUsers(@RequestParam("nat") String nation);

    @PostMapping("empyloee/create")
    HttpStatus createEmplyoee(@RequestBody LoginMemberResDto request);
}
