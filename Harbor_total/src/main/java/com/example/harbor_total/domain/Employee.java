package com.example.harbor_total.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/*
* employeeId: 사원 번호(PK)
* email: 이메일
* name: 이름
* teamName: 소속팀(FK)
* annualRemain: 잔여 연차
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Employee")
public class Employee {
    @Id
    @Column(length = 20)
    private String employeeId;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String teamName;
    private Double annualRemain;

    @OneToMany(mappedBy = "employee")
    private List<Commute> commutes;

    @OneToMany(mappedBy = "employee")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

}
