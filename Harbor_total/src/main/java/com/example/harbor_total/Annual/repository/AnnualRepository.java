package com.example.harbor_total.Annual.repository;

import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnnualRepository extends JpaRepository<Annual, Long> {
//    Optional<Annual> findByAdjustmentDateBetweenAndAttendanceWorkStartTimeAndAttendanceWorkEndTime(LocalDateTime start, LocalDateTime end, LocalDateTime attendanceStart, LocalDateTime attendanceEnd);
    Optional<Annual> findByAdjustmentDateLessThanAndAdjustmentEndDateGreaterThanEqualOrAdjustmentDateLessThanEqualAndAdjustmentEndDateGreaterThan(
        LocalDateTime endDate1, LocalDateTime startDate1,
        LocalDateTime startDate2, LocalDateTime endDate2);



    @Query("SELECT e FROM Annual e WHERE e.firstSignId = :employeeId OR e.secondSignId = :employeeId OR e.thirdSignId = :employeeId")
    List<Annual> findAllByFirstSignIdOrSecondSignIdOrThirdSignId(@Param(value = "employeeId") @NotEmpty String employeeId);
}
