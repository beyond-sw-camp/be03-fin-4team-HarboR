package com.example.harbor_login.Login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    @GetMapping("/login/admin")
    public ResponseEntity<String> healthCheck(){
        System.out.println("접근 성공");
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }
}
