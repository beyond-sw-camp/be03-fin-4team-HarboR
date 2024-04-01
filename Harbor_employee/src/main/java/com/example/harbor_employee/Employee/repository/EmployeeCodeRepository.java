package com.example.harbor_employee.Employee.repository;

import com.example.harbor_employee.Employee.domain.EmployeeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeCodeRepository extends JpaRepository<EmployeeCode, String> {
    Optional<EmployeeCode> findByDescription(String description);
    Optional<EmployeeCode> findByCode(String code);
}
