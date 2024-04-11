package com.example.harbor_total.Employee.domain;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.domain.Commute;
import com.example.harbor_total.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/*

 * employeeId: 사원 번호(PK)
 * email: 이메일
 * name: 이름
 * teamName: 소속팀(FK)
 * annualRemain: 잔여 연차
 * */
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @Column(length = 20)
    private String employeeId;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String teamCode;
    @Column(nullable = false, length = 20)
    private String departmentCode;
    @Column(nullable = false, length = 20)
    private String positionCode;
    private double annualRemain;

    @OneToMany(mappedBy = "employee")
    private List<Commute> commutes;

    @OneToMany(mappedBy = "employee")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    public static Employee create(String id, String email, String name, String teamCode, String departmentCode, String positionCode, double annual){
        return Employee.builder()
                .employeeId(id)
                .email(email)
                .name(name)
                .teamCode(teamCode)
                .departmentCode(departmentCode)
                .positionCode(positionCode)
                .annualRemain(annual)
                .build();
    }
}
