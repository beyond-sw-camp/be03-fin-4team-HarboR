package com.example.harbor_total.Attendance.domain;

import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.Employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String workPolicy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annual_id")
    private Annual annuals;

    public static Attendance create(Employee employee, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto) {
        return Attendance.builder()
                .workStartTime(attendanceFlexibleWorkReqDto.getWorkStartTime())
                .workEndTime(attendanceFlexibleWorkReqDto.getWorkEndTime())
                .workPolicy(attendanceFlexibleWorkReqDto.getWorkPolicy())
                .employee(employee)
                .build();
    }

    public void updateAttendanceId(Annual annuals) {
        this.annuals = annuals;
    }
}
/*
* attendanceId: 근태 번호(PK, AUTO)
* employeeId: 사원 번호(FK)
* workStartTime: 시작 시각
* workEndTime: 종료 시각
* workTotalTime: 총 근무
* workPolicy: 근무 정책(코드)
* workType: 근무 유형(코드)
* */
