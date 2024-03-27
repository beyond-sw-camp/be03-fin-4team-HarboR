package com.example.harbor_employee.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/*
* departmentCode: 부서(PK)(코드)
* highDepartmentCode: 상위 부서(코드)
* departmentName: 부서명
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Department")
public class Department {
    @Id
    private String departmentCode;
    private String highDepartmentCode;
    @Column(nullable = false, length = 20)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}