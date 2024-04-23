package com.example.harbor_total.Employee.controller;

import com.example.harbor_total.Employee.service.EmployeeService;
import com.example.harbor_total.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/total/employee")
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/annualremain")
    public ResponseEntity<CommonResponse> annualremain(@RequestHeader("employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("남은 휴가 확인", employeeService.annualremain(employeeId)), HttpStatus.OK);
    }
}
