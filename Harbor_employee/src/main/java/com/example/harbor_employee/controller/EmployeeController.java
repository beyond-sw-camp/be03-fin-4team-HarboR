package com.example.harbor_employee.controller;

import com.example.harbor_employee.client.GetUsersResponse;
import com.example.harbor_employee.domain.Employee;
import com.example.harbor_employee.repository.EmployeeRepository;
import com.example.harbor_employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

}
