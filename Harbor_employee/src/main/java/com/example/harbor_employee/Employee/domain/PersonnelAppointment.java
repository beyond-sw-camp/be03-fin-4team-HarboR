package com.example.harbor_employee.Employee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/*
* appointmentId: 인사발령 번호(PK)
* employeeId: 사원번호(FK)
* updateDutyCode: 변경된 직무
* beforeDepartmentCode: 변경 전 부서 코드
* afterDepartmentCode: 변경 후 부서 코드
* positionCode: 직위 코드
* issueDate: 발령 날짜
* */

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_PA")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonnelAppointment {
    @Id
    private Long appointmentId;

    @Column(length = 20)
    private String updateDutyCode;
    @Column(length = 20)
    private String beforeDepartmentCode;
    @Column(length = 20)
    private String afterDepartmentCode;
    @Column(length = 20)
    private String positionCode;
    @CreatedDate
    private String issueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
