package com.example.harbor_employee.Employee.domain;

import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @Entity 구성
* @employeeId: 사원번호(PK), 8자리
* @teamCode: 소속팀(코드)
* @positionCode: 직위(코드)
* @statusCode: 업무 상태(코드)
* @genderCode: 성별(코드)
* @dutyCode: 직무(코드)
* @bankCode: 은행(코드)
* @profile_image: 사진 url
* @email: 이메일
* @name: 이름
* @phone: 전화번호
* @socialSecurityNumber: 주민등록번호
* @birthDate: 생년월일
* @address: 주소
* @year: 년차
* @joinDate: 입사날짜
* @leavingDate: 퇴사날짜
* @updateDate: 수정날짜
* @reasonForResignation: 퇴사사유
* @account: 계좌번호
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @Column(nullable = false, length = 20)
    private String employeeId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_code")
    private EmployeeCode teamCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_code")
    private EmployeeCode positionCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private EmployeeCode statusCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_code")
    private EmployeeCode genderCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "duty_code")
    private EmployeeCode dutyCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code")
    private EmployeeCode bankCode;
    @Column(length = 1000)
    private String profileImage;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 20)
    private String phone;
    @Column(length = 20)
    private String socialSecurityNumber;
    @Column(nullable = false, length = 20)
    private String birthDate;
    private String address;
    private String careerYMD;
    private String joinDate;
    private String leavingDate;
    private String updateDate;
    private String reasonForResignation;
    @Column(length = 50)
    private String accountNumber;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Eworks> eworks = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<PersonnelAppointment> personnelAppointmentList = new ArrayList<>();

    public static Employee createLogin(String email, String birth, String name,String employeeId) {
        return Employee.builder()
                .email(email)
                .birthDate(birth)
                .name(name)
                .employeeId(employeeId)
                .build();
    }

    public void updatePA(PersonnelAppointment pa, Department department){
        this.dutyCode = pa.getUpdateDutyCode();
        this.positionCode = pa.getPositionCode();
        this.joinDate = pa.getIssueDate();
        this.department = department;
    }
}

