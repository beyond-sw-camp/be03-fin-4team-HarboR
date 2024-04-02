package com.example.harbor_employee.PersonnelAppointment.domain;

import com.example.harbor_employee.Employee.domain.Department;
import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.domain.EmployeeCode;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonnelAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "update_duty_code")
    private EmployeeCode updateDutyCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "before_department_code")
    private EmployeeCode beforeDepartmentCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "after_department_code")
    private EmployeeCode afterDepartmentCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_code")
    private EmployeeCode positionCode;

    private String issueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Boolean delYn = false;

    public PersonnelAppointment(Employee employee, EmployeeCode beforeDepartmentCode, EmployeeCode afterDepartmentCode, EmployeeCode positionCode, String issueDate, EmployeeCode updateDutyCode) {
        this.employee = employee;
        this.beforeDepartmentCode =beforeDepartmentCode;
        this.afterDepartmentCode = afterDepartmentCode;
        this.positionCode = positionCode;
        this.issueDate = issueDate;
        this.updateDutyCode =updateDutyCode;
    }

    public static PersonnelAppointment CreatePA(Employee employee, EmployeeCode beforeDepartmentCode, EmployeeCode afterDepartmentCode, EmployeeCode positionCode, String issueDate, EmployeeCode updateDutyCode) {
        return new PersonnelAppointment(employee, beforeDepartmentCode, afterDepartmentCode, positionCode, issueDate, updateDutyCode);
    }
    public void before_to_After(EmployeeCode beforeDepartmentCode) {
        this.afterDepartmentCode = beforeDepartmentCode;
    }
}
