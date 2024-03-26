package com.example.harbor_login.controller;

import com.example.harbor_login.dto.LoginReqDTO;
import com.example.harbor_login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/dologin")
    public String doLogin(@RequestBody LoginReqDTO loginReqDTO){
        return "ok";
    }
}
