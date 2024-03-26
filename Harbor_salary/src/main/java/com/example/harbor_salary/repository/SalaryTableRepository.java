package com.example.harbor_salary.repository;

import com.example.harbor_salary.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryTableRepository extends JpaRepository<Salary, Long > {
}
