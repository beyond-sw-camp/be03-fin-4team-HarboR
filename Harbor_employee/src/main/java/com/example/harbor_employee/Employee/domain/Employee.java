package com.example.harbor_employee.Employee.domain;

import com.example.harbor_employee.Employee.dto.response.ExcelEmployeeDto;
import com.example.harbor_employee.Eworks.domain.Eworks;
import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @Entity 구성
* @employeeId 사원번호(PK), 8자리
* @teamCode 소속팀(코드)
* @positionCode 직위(코드)
* @statusCode 업무 상태(코드)
* @genderCode 성별(코드)
* @dutyCode 직무(코드)
* @bankCode 은행(코드)
* @profile_image 사진 url
* @email 이메일
* @name 이름
* @phone 전화번호
* @socialSecurityNumber 주민등록번호
* @birthDate 생년월일
* @address 주소
* @year 년차
* @joinDate 입사날짜
* @leavingDate 퇴사날짜
* @updateDate 수정날짜
* @reasonForResignation 퇴사사유
* @account 계좌번호
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
    @Column(length = 20)
    private String teamCode;
    @Column(length = 20)
    private String positionCode;
    @Column(length = 20)
    private String statusCode;
    @Column(length = 20)
    private String genderCode;
    @Column(length = 20)
    private String dutyCode;
    @Column(length = 20)
    private String bankCode;

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
    private String reasonForResignation;
    @Column(length = 50)
    private String accountNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String departmentCode;

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

    //    스트링으로 바뀜
    public void setImage(String image) {
        this.profileImage = image;
    }
    public void updatePA(PersonnelAppointment pa){
        this.dutyCode = pa.getUpdateDutyCode();
        this.positionCode = pa.getPositionCode();
        this.joinDate = pa.getIssueDate();
        this.departmentCode = pa.getAfterDepartmentCode();
    }

    public void updateEmployee(String updateimage, String phone) {

        this.profileImage = updateimage;
        this.phone = phone;
    }

    public void updateEmployee(ExcelEmployeeDto excelEmployeeDto) {
        this.teamCode = excelEmployeeDto.getTeamCode();
        this.positionCode = excelEmployeeDto.getPositionCode();
        this.dutyCode = excelEmployeeDto.getDutyCode();
        this.statusCode = excelEmployeeDto.getStatusCode();
        this.genderCode = excelEmployeeDto.getGenderCode();
        this.bankCode = excelEmployeeDto.getBankCode();
        this.socialSecurityNumber = excelEmployeeDto.getSocialSecurityNumber();
        this.address = excelEmployeeDto.getAddress();
        this.careerYMD = excelEmployeeDto.getCareerYMD();
        this.joinDate = excelEmployeeDto.getJoinDate();
        this.accountNumber = excelEmployeeDto.getAccountNumber();
        this.departmentCode = excelEmployeeDto.getDepartmentCode();
        this.phone = excelEmployeeDto.getPhone();
    }
}

