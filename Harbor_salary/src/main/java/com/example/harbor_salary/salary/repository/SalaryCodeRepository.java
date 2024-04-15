package com.example.harbor_salary.salary.repository;

import com.example.harbor_salary.salary.domain.SalaryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryCodeRepository extends JpaRepository<SalaryCode, String> {
        SalaryCode findByCodeNum(String CodeNum);


}
