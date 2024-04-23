package com.example.harbor_total.Employee.service;

import com.example.harbor_total.Employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public double annualremain(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException(" 없는 회원입니다")).getAnnualRemain();
    }
}
