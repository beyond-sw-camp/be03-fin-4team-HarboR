package com.example.harbor_employee.Employee.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hr_department")
@Getter
public class Department {
    @Id
    private String departmentCode;
    private String highDepartmentCode;
}
