package com.example.harbor_total.commute.domain;

import com.example.harbor_total.Employee.domain.Employee;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Commute")
@ToString
public class CommuteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commuteId;
    private Time attendanceTime;
    private Time leaveworkTime;
    private Date attendanceDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public CommuteRecord(Employee employee) {
        this.employee = employee;
        this.attendanceDate = Date.valueOf(LocalDate.now());
        this.attendanceTime = Time.valueOf(LocalTime.now());
    }

    public static CommuteRecord createCommuteRecord(Employee employee) {
        return new CommuteRecord(employee);
    }

    public void updateLeaveTime(Time leavTime) {
        this.leaveworkTime = leavTime;
    }
}
