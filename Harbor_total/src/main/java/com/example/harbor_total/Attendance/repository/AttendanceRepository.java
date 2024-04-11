package com.example.harbor_total.Attendance.repository;

import com.example.harbor_total.Attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    //    List<Attendance> findByEmployeeEmployeeIdAndWorkStartTimeBetween(String employeeId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Attendance> findByWorkStartTimeBetweenAndEmployeeEmployeeId(LocalDateTime workStartTime, LocalDateTime workStartTime2, String employee_employeeId);

}
