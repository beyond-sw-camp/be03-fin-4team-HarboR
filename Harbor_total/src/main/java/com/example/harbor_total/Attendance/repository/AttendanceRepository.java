package com.example.harbor_total.Attendance.repository;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    //    List<Attendance> findByEmployeeEmployeeIdAndWorkStartTimeBetween(String employeeId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Attendance> findByWorkStartTimeBetweenAndEmployeeEmployeeId(LocalDateTime workStartTime, LocalDateTime workStartTime2, String employee_employeeId);

    List<Attendance> findAllByEmployee(Employee employee);

    @Query("SELECT a.attendanceId FROM Attendance a WHERE a.employee.employeeId = :employeeId AND a.workPolicy = :workPolicy")
    List<Long> findAttendanceIdsByEmployeeIdAndWorkPolicy(@Param("employeeId") String employeeId, @Param("workPolicy") String workPolicy);


}
