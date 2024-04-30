package com.example.harbor_total.Employee.controller;

import com.example.harbor_total.Employee.service.EmployeeService;
import com.example.harbor_total.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/name/{employeeId}")
    public ResponseEntity<CommonResponse> employeeName(@PathVariable("employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("이름 확인", employeeService.employeeName(employeeId)), HttpStatus.OK);
    }
}
