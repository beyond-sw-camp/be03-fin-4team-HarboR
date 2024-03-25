package com.example.harbor_salary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@Entity
@Table(name = "HR_Salary")
public class SalaryTable {

    @Id
    private String position;

    @Column(nullable = false)
    private int salary;


}
