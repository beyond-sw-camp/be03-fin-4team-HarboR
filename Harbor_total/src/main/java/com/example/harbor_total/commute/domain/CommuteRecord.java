package com.example.harbor_total.commute.domain;

import com.example.harbor_total.Employee.domain.Employee;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Commute")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
