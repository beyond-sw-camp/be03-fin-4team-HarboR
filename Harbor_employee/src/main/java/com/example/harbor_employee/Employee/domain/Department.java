package com.example.harbor_employee.Employee.domain;

import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
* @departmentCode: 부서(PK)(코드)
* @highDepartmentCode: 상위 부서(코드)
* @departmentName: 부서명
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Department")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private String departmentCode;
    private String highDepartmentCode;
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}