package com.example.harbor_employee.schedule.domain;

import com.example.harbor_employee.Employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;

/*
 * scheduleId: 일정 번호(PK)
 * employeeId: 사원 번호(FK)
 * scheduleStartDate: 시작 날짜
 * scheduleEndDate: 종료 날짜
 * scheduleStartTime: 시작 시간
 * scheduleEndTime: 종료 시간
 * scheduleTitle: 일정 제목
 * scheduleComment: 일정 내용
 * */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Schedule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private String scheduleStartTime;
    private String scheduleEndTime;
    @Column(nullable = false, length = 50)
    private String scheduleTitle;
    @Column(length = 500)
    private String scheduleComment;
    @Builder.Default
    private String scheduleColor = "yellow";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private boolean delYn= false;


    public void updateSchedule(String scheduleStartDate, String scheduleEndDate,
                               String scheduleEndTime, String scheduleStartTime,
                               String scheduleComment, String scheduleTitle) {
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.scheduleEndTime = scheduleEndTime;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleComment = scheduleComment;
        this.scheduleTitle = scheduleTitle;
    }

    public void deleteSchedule(){
        this.delYn = true;
    }
}
