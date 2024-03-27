package com.example.harbor_salary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HR_salaryTable")
public class SalaryCode {
    //코드번호
    @Id
    @Column(length = 20)
    private String code_num;
    //급여
    @Column(nullable = false, length = 20)
    private int salary;
    //직위
    @Column(nullable = false, length = 10)
    private String position;
    //통상 시급
    @Column(nullable = false, length = 10)
    private int salaryWage;
    //고정연장수당
    @Column(nullable = false, length = 10)
    private int salaryOvertime;

}
