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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String adjustment_delYn;
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

//    public static Annual create(Double annualCount, LocalDateTime adjustmentDate, LocalDateTime adjustmentEndDate, String adjustmentComment, String firstSignId, String secondSignId, String thirdSignId, Attendance attendance){
    public static Annual create(Double annualCount, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, Attendance attendance){
//        return Annual.builder()
//                .annualCount(annualCount)
//                .adjustmentDate(adjustmentDate)
//                .adjustmentEndDate(adjustmentEndDate)
//                .adjustmentComment(adjustmentComment)
//                .firstSignId(firstSignId)
//                .secondSignId(secondSignId)
//                .thirdSignId(thirdSignId)
//                .attendance(attendance)
//                .build();
        return new Annual(annualCount,
                attendanceFlexibleWorkReqDto.getWorkStartTime(),
                attendanceFlexibleWorkReqDto.getWorkEndTime(),
                attendanceFlexibleWorkReqDto.getAdjustmentComment(),
                attendanceFlexibleWorkReqDto.getFirstSignId(),
                attendanceFlexibleWorkReqDto.getSecondSignId(),
                attendanceFlexibleWorkReqDto.getThirdSignId(),
                attendance);
//        return new Annual(annualCount, adjustmentDate, adjustmentEndDate, adjustmentComment, firstSignId, secondSignId, thirdSignId, attendance);
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
        this.adjustment_delYn = "Y";
    }
}
