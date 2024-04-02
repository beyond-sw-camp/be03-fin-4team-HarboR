package com.example.harbor_employee.Employee.service;

import com.example.harbor_employee.Employee.domain.EmployeeCode;
import com.example.harbor_employee.Employee.repository.EmployeeCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeCodeService {
    private EmployeeCodeRepository employeeCodeRepository;

    public List<EmployeeCode> getCodes(List<String> codes){
        return codes.stream()
                .map(e -> employeeCodeRepository.findByCode(e)
                        .orElseThrow(IllegalArgumentException::new))
                .collect(Collectors.toList());
    }
}
