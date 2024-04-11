package com.example.harbor_total.Annual.repository;

import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AnnualRepository extends JpaRepository<Annual, Long> {
//    Optional<Annual> findByAdjustmentDateBetweenAndAttendanceWorkStartTimeAndAttendanceWorkEndTime(LocalDateTime start, LocalDateTime end, LocalDateTime attendanceStart, LocalDateTime attendanceEnd);
Optional<Annual> findByAdjustmentDateLessThanAndAdjustmentEndDateGreaterThanEqualOrAdjustmentDateLessThanEqualAndAdjustmentEndDateGreaterThan(
        LocalDateTime endDate1, LocalDateTime startDate1,
        LocalDateTime startDate2, LocalDateTime endDate2);
}
