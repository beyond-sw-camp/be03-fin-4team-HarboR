package com.example.harbor_total.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

/*
* commuteId: 출퇴근 기록 번호(PK)
* employeeId: 사원 번호(FK)
* attendanceTime: 출근 시각
* leaveworkTime: 퇴근 시각
* breakTime: 휴계 시간
* attendanceDate: 날짜
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Commute")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Commute {
    @Id
    private Long commuteId;
    private Time attendanceTime;
    private Time leaveworkTime;
    private Time breakTime;
    //Todo: 컬럼 생성날짜로 해도 괜찮을지도?
    private String attendanceDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
