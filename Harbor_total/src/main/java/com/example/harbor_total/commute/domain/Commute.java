package com.example.harbor_total.domain;

import com.example.harbor_total.dto.AttendanceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

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
    private String status_code;
    //Todo: 컬럼 생성날짜로 해도 괜찮을지도?
    private String attendanceDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public void changeAttendanceTime(Time attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public void changeLeaveWorkTime(Time leaveworkTime) {
        this.leaveworkTime = leaveworkTime;
    }


    public void changeStatus(String status_code) {
        this.status_code = status_code;
    }

    @Builder
    public Attendance(Time attendanceTime, Time leaveworkTime, String status_code, Employee employee){
            this.attendanceTime = attendanceTime;
            this.leaveworkTime = leaveworkTime;
            this.status_code = status_code;
            this.employee = employee;
    }
    public AttendanceDTO.Index entityToIndex(){
        return AttendanceDTO.Index.builder()
                .attendanceTime(attendanceTime)
                .leaveworkTime(leaveworkTime)
                .status_code(status_code)
                . e()
                .build();
    }

}