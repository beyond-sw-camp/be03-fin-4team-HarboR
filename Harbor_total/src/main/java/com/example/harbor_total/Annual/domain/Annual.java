package com.example.harbor_total.Annual.domain;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.global.support.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
* annualId: 휴가 번호(PK)
* attendanceId: 근태 번호(FK)
* annualCount: 휴가 추가/차감일
* adjustmentDate: 조정 적용일
* adjustmentComment: 조정 사유
* adjustmentUpdateDate: 승인 날짜
* authorityPersonId: 최종 승인권자
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Annual")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualId;
    private Double annualCount;
    private LocalDateTime adjustmentDate;
    private LocalDateTime adjustmentEndDate;
    private String adjustmentComment;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;
    private Boolean adjustment_delYn = false;
    @OneToOne(mappedBy = "annuals", cascade = CascadeType.ALL)
    private Attendance attendance;

    public static Annual create(Double annualCount, LocalDateTime adjustmentDate, LocalDateTime adjustmentEndDate, String adjustmentComment, String firstSignId, String secondSignId, String thirdSignId, Attendance attendance){
        return Annual.builder()
                .annualCount(annualCount)
                .adjustmentDate(adjustmentDate)
                .adjustmentEndDate(adjustmentEndDate)
                .adjustmentComment(adjustmentComment)
                .firstSignId(firstSignId)
                .secondSignId(secondSignId)
                .thirdSignId(thirdSignId)
                .attendance(attendance)
                .build();
    }

    public void updateApprovalDate(Approval approval){
        if(approval.name().equals("FIRST")){
            this.firstApprovalDate = String.valueOf(LocalDate.now());
        }else if(approval.name().equals("SECOND")){
            this.secondApprovalDate = String.valueOf(LocalDate.now());
        }else if(approval.name().equals("THIRD")){
            this.thirdApprovalDate = String.valueOf(LocalDate.now());
        }
    }

    public void updateCompanion(Approval approval){
        if(approval.name().equals("FIRST")){
            this.firstApprovalDate = "companion";
        }else if(approval.name().equals("SECOND")){
            this.secondApprovalDate = "companion";
        }else if(approval.name().equals("THIRD")){
            this.thirdApprovalDate = "companion";
        }
    }

    public void updateDelYN(){
        if(this.adjustment_delYn) this.adjustment_delYn = false;
        this.adjustment_delYn = true;
    }
}
