package com.example.harbor_total.Employee.service;

import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
