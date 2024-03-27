package com.example.harbor_salary.repository;

import com.example.harbor_salary.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long > {
        List<Salary> findByEmployeeId(int employeeId);
}
