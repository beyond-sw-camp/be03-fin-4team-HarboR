package com.example.harbor_employee.service;

import com.example.harbor_employee.dto.LoginMemberResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public HttpStatus createBasicEmployee(LoginMemberResDto loginMemberResDto) {

        log.info(" basicEmployee 객체 생성");
        System.out.println("loginMemberResDto = " + loginMemberResDto.getBirth());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getName());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getEmail());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getEmployeeId());
        Employee basicEmployee = Employee.createLogin(
                loginMemberResDto.getEmail(),
                loginMemberResDto.getBirth(),
                loginMemberResDto.getName(),
                loginMemberResDto.getEmployeeId()
        );
        employeeRepository.save(basicEmployee);
        return HttpStatus.OK;
    }
}
