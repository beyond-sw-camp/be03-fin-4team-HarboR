package com.example.harbor_salary.salary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_SalaryCode")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryCode {
    //코드번호
    @Id
    @Column(length = 20)
    private String codeNum;
    //급여
    @Column(nullable = false)
    private int baseSalary;
    //직위
    @Column(nullable = false, length = 10)
    private String position;

}
