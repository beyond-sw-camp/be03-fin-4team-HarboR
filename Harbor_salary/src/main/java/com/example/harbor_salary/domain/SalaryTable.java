package com.example.harbor_salary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HR_salaryTable")
public class SalaryTable {
    //코드번호
    @Id
    @Column(length = 5)
    private String code_num;
    //급여
    @Column(nullable = false)
    private int salary;
    //직위
    @Column(nullable = false)
    private String position;
    //통상 시급
    @Column(nullable = false)
    private int salaryWage;
    //고정연장수당
    @Column

    private int salaryOvertime;

}
