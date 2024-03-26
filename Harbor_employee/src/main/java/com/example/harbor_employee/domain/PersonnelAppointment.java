package com.example.harbor_employee.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_PA")
public class PersonnelAppointment {
    @Id
    @Column(nullable = false, length = 8)
    private Integer appointmentId;

    private String updateDutyCode;
    private String beforeDepartmentCode;
    private String afterDepartmentCode;
    private String positionCode;
    private String issueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
