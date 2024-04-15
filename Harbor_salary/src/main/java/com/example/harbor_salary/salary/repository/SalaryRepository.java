package com.example.harbor_salary.salary.repository;

import com.example.harbor_salary.salary.domain.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
        Salary findByEmployeeIdAndSalaryId(String employeeId , Long salaryId);

        Page<Salary> findAllByEmployeeId(String employeeId, Pageable pageable);

        List<Salary> findTop3ByEmployeeIdOrderBySalaryMonthOfYearDesc(String employeeId);

        @Query("SELECT COUNT(e) FROM Salary e WHERE e.employeeId = :employeeId")
        int getCountByEmployeeId(String employeeId);
}
