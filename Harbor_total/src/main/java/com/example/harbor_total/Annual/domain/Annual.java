package com.example.harbor_total.Annual.domain;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.global.support.Approval;
import lombok.*;
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
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Annual")
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
    @Builder.Default
    private String adjustment_delYn = "N";
    @OneToOne(mappedBy = "annuals")
    private Attendance attendance;
    public Annual(Double annualCount, LocalDateTime adjustmentDate, LocalDateTime adjustmentEndDate, String adjustmentComment, String firstSignId, String secondSignId, String thirdSignId, Attendance attendance) {
        this.annualCount = annualCount;
        this.adjustmentDate = adjustmentDate;
        this.adjustmentEndDate = adjustmentEndDate;
        this.adjustmentComment = adjustmentComment;
        this.attendance = attendance;
        this.firstSignId = firstSignId;
        this.secondSignId = secondSignId;
        this.thirdSignId = thirdSignId;
    }

    public static Annual create(Double annualCount, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, Attendance attendance){

        return new Annual(annualCount,
                attendanceFlexibleWorkReqDto.getWorkStartTime(),
                attendanceFlexibleWorkReqDto.getWorkEndTime(),
                attendanceFlexibleWorkReqDto.getAdjustmentComment(),
                attendanceFlexibleWorkReqDto.getFirstSignId(),
                attendanceFlexibleWorkReqDto.getSecondSignId(),
                attendanceFlexibleWorkReqDto.getThirdSignId(),
                attendance);
    }

    public void updateApprovalDate(Approval approval){
        switch (approval.name()) {
            case "FIRST":
                this.firstApprovalDate = String.valueOf(LocalDate.now());
                break;
            case "SECOND":
                this.secondApprovalDate = String.valueOf(LocalDate.now());
                break;
            case "THIRD":
                this.thirdApprovalDate = String.valueOf(LocalDate.now());
                break;
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
        this.adjustment_delYn = "Y";
    }
}
