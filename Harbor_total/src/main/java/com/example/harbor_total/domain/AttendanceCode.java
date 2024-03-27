package com.example.harbor_total.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* codeNum: 코드 번호(PK)
* codeName: 코드 이름
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Attendance_Code")
public class AttendanceCode {
    @Id
    private String codeNum;
    private String codeName;
}
