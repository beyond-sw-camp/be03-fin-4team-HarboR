package com.example.harbor_salary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class salaryTable {

    @Id
    private String position;

    @Column(nullable = false)
    private int salary;

}
