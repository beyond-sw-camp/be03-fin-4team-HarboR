package com.example.harbor_total.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

/*
* attendanceId: 근태 번호(PK, AUTO)
* employeeId: 사원 번호(FK)
* workStartTime: 시작 시각
* workEndTime: 종료 시각
* workTotalTime: 총 근무
* workPolicy: 근무 정책(코드)
* workType: 근무 유형(코드)
* */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;
    private Time workStartTime;
    private Time workEndTime;
    private Time workTotalTime;
    private String workPolicy;
    private String workType;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "attendance")
    private List<Annual> annuals;

}
