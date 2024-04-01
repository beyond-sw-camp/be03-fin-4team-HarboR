package com.example.harbor_total.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
    private String adjustmentDate;
    private String adjustmentComment;
    private String adjustmentUpdateDate;
    private Integer authorityPersonId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;
}
