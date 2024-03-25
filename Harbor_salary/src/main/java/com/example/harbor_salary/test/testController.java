package com.example.harbor_salary.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/salary/member")
public class testController {
    @GetMapping("/test")
    public String test() {
        System.out.println("여기까지 들어옴");
        return "ok1";
    }
}