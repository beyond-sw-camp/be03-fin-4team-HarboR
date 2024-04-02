package com.example.harbor_employee.Employee.controller;

import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee/pa")
public class PAController {
    @PostMapping("/create")
    public static ResponseEntity<CommonResponse> createPA(@RequestBody ){

    }
}
