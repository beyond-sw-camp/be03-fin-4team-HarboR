package com.example.harbor_employee.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/*
* codeNum: 코드 번호(PK)
* nodeName: 해당 코드 이름
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Code")
public class EmployeeCode {
    @Id
    @Column(nullable = false, length = 20)
    private String codeNum;
    @Column(nullable = false, length = 20)
    private String codeName;
}
