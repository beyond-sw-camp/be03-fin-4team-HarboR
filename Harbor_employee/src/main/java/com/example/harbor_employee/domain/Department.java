package com.example.harbor_employee.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Department")
public class Department {
    @Id
    private String departmentCode;
    private String highDepartmentCode;
}
