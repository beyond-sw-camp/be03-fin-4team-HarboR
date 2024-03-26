package com.example.harbor_salary.entity;

import com.example.harbor_salary.domain.SalaryTable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "HR_salary")
public class Salary{
    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annual_id;

    //직위
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SalaryTable salaryTable;
    //사원 번호
    @Column(nullable = false)
    private int employee_id;

    //해당년월
    @CreationTimestamp
    private LocalDate salary_month_of_year;
    //기본급
    @Column(nullable = false)
    private int salary_base;
    //초과근무수당(포괄)
    @Column(nullable = false)
    private int salary_overtime;
    //통상 시급
    @Column(nullable = false)
    private int salary_wage;
}
