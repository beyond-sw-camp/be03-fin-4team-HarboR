package com.example.harbor_total.commute.repository;


import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.commute.domain.CommuteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommuteRepository extends JpaRepository<CommuteRecord,Long> {
    Optional<CommuteRecord> findByCommuteId(Long CommuteId);

    Optional<CommuteRecord> findByEmployeeAndAttendanceDate(Employee employee, Date attendanceDate);

    boolean existsByEmployeeAndAttendanceDate(Employee employee, Date attendanceDate);

    List<CommuteRecord> findAllByEmployeeAndAttendanceDateBetween(Employee employee, Date StartTime, Date EndTime);

}
