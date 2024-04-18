package com.example.harbor_total.Attendance.domain;

import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.global.support.Code;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "HR_Attendance")
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

    public Attendance(Employee employee, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto) {
        this.workStartTime = attendanceFlexibleWorkReqDto.getWorkStartTime();
        this.workEndTime = attendanceFlexibleWorkReqDto.getWorkEndTime();
        this.workPolicy = attendanceFlexibleWorkReqDto.getWorkPolicy();
        this.employee = employee;
    }

    public Attendance(Employee employee) {
        this.employee = employee;
        this.workStartTime = LocalDate.now().atTime(9, 0);
        this.workPolicy = String.valueOf(Code.O01);
    }

    public static Attendance create(Employee employee, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto) {
        return new Attendance(employee, attendanceFlexibleWorkReqDto);
    }
    public static Attendance create(Employee employee) {
        return new Attendance(employee);
    }
    public void updateAttendanceId(Annual annuals) {
        this.annuals = annuals;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", workStartTime=" + workStartTime +
                ", workEndTime=" + workEndTime +
                ", workPolicy='" + workPolicy + '\'' +
                ", employee=" + employee.getEmployeeId() + // 예시로 연관된 필드를 추가
                '}';
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
