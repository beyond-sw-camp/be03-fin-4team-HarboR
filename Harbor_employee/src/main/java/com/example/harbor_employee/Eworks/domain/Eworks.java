package com.example.harbor_employee.Eworks.domain;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.global.support.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Entity 구성
 * @payId: 결재 번호(PK)
 * @employeeId: 사원번호(FK)
 * @payStatusCode 결재타입코드(코드)
 * @approvalDate 승인일자
 * @firstSignId 1차 승인권자 사번
 * @secondSignId 2차 승인권자 사번
 * @thirdSignId 3차 승인권자 사번
 * */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_Eworks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Eworks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false, length = 20)
    private String payStatusCode;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;

    public static Eworks createEworks(Employee employee, String payStatusCode, String firstSignId, String secondSignId, String thirdSignId){
        return Eworks.builder()
                .employee(employee)
                .payStatusCode(payStatusCode)
                .firstSignId(firstSignId)
                .secondSignId(secondSignId)
                .thirdSignId(thirdSignId)
                .build();
    }

    public void updateApprovalDate(Approval approval){
        if(approval.name().equals("FIRST")){
            this.firstApprovalDate = String.valueOf(LocalDateTime.now()).substring(0, 10);
        }else if(approval.name().equals("SECOND")){
            this.secondApprovalDate = String.valueOf(LocalDateTime.now()).substring(0,10);
        }else if(approval.name().equals("THIRD")){
            this.thirdApprovalDate = String.valueOf(LocalDateTime.now()).substring(0,10);
        }
    }
}
