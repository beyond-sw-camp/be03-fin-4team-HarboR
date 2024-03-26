package com.example.harbor_employee.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/*
 * Entity 구성
 * payId: 결재 번호(PK)
 * employeeId: 사원번호(FK)
 * payStatusCode 결재타입코드(코드)
 * approvalDate 승인일자
 * firstSignId 1차 승인권자 사번
 * secondSignId 2차 승인권자 사번
 * thirdSignId 3차 승인권자 사번
 * */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_Eworks")
public class Eworks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payId;

    @Column(nullable = false, length = 10)
    private String payStatusCode;
    private String approvalDate;
    @Column(nullable = false)
    private Integer firstSignId;
    private Integer secondSignId;
    private Integer thirdSignId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
