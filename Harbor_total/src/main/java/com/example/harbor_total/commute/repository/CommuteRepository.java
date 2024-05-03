package com.example.harbor_total.commute.repository;


import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.commute.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommuteRepository extends JpaRepository<Commute,Long> {
    Optional<Commute> findByCommuteId(Long CommuteId);

    Optional<Commute> findByEmployeeAndAttendanceDate(Employee employee, Date attendanceDate);

    boolean existsByEmployeeAndAttendanceDate(Employee employee, Date attendanceDate);

    List<Commute> findAllByEmployeeAndAttendanceDateBetween(Employee employee, Date StartTime, Date EndTime);

}
