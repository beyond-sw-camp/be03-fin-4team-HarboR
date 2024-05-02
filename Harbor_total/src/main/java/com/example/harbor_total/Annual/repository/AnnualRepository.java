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

    Optional<Annual> findByAnnualId(Long annualId);
//    내가 휴가 신청 한 날 사이에 다른 휴가가 있는지 확인
    @Query("SELECT a FROM Annual a WHERE a.adjustmentDate <= :endDate AND a.adjustmentEndDate >= :startDate AND a.adjustmentDate <= :startDate2 AND a.adjustmentEndDate >= :endDate2 AND a.annualId IN :attendanceIds")
    List<Annual> CheckMyVactionSchedule(@Param("endDate") LocalDateTime endDate, @Param("startDate") LocalDateTime startDate, @Param("startDate2") LocalDateTime startDate2, @Param("endDate2") LocalDateTime endDate2, @Param("attendanceIds") List<Long> attendanceIds);

    @Query("SELECT e FROM Annual e WHERE e.adjustment_delYn=:adjustment_delYn AND e.firstSignId = :employeeId OR e.secondSignId = :employeeId OR e.thirdSignId = :employeeId")
    List<Annual> findAllByFirstSignIdOrSecondSignIdOrThirdSignIdAAndAdjustment_delYnIs(@Param(value = "employeeId") @NotEmpty String employeeId, @Param(value="adjustment_delYn") String adjustment_delYn);
}
