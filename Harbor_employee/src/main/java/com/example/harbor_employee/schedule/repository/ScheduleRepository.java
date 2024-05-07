package com.example.harbor_employee.schedule.repository;

import com.example.harbor_employee.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
        List<Schedule> findAllByDelYnIsAndEmployee_EmployeeId(boolean delYn, String employee_employeeId);
        Optional<Schedule> findByScheduleId(Long scheduleId);

        @Query("SELECT s FROM Schedule s WHERE s.delYn = :delYn AND s.employee.departmentCode = :departmentCode")
        List<Schedule> findAllByDelYnIsAndEmployee_DepartmentCode(@Param("delYn") boolean delYn, @Param("departmentCode") String departmentCode);
}
