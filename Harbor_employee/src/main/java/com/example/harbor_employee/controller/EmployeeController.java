package com.example.harbor_employee.controller;

import com.example.harbor_employee.client.GetUsersResponse;
import com.example.harbor_employee.domain.Employee;
import com.example.harbor_employee.dto.LoginMemberResDto;
import com.example.harbor_employee.repository.EmployeeRepository;
import com.example.harbor_employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/empyloee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public HttpStatus BasicCreateEmployee(@RequestBody LoginMemberResDto loginMemberResDto) {
        log.info(" 호출 시작 : ");
        return employeeService.createBasicEmployee(loginMemberResDto);
    }
}
