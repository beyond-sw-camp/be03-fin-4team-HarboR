package com.example.harbor_salary.repository;

import com.example.harbor_salary.domain.SalaryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryCodeRepository extends JpaRepository<SalaryCode, String> {
        SalaryCode findByCodeNum(String CodeNum);
}
