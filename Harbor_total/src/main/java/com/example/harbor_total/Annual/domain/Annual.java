package com.example.harbor_total.Annual.domain;

import com.example.harbor_total.Attendance.domain.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Long annualId;
    private Integer annualCount;
    private LocalDateTime adjustmentDate;
    private LocalDateTime adjustmentEndDate;
    private String adjustmentComment;
    private LocalDateTime adjustmentUpdateDate;
    private Integer authorityPersonId;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;
    private LocalDateTime firstApprovalDate;
    private LocalDateTime secondApprovalDate;
    private LocalDateTime thirdApprovalDate;
    private Boolean adjustment_delYn = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;
}
