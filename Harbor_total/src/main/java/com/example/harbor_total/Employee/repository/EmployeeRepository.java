package com.example.harbor_total.Employee.repository;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmployeeId(String EmployeeId);

    @Query("SELECT e FROM Employee e WHERE e.positionCode > :startDeptCode AND e.positionCode <= :endDeptCode AND e.departmentCode = :departmentCode")
    List<Employee> findEmployeesByDepartmentCodeAndPositionCodeRange(@Param(value = "startDeptCode") @NotEmpty String startDeptCode,
                                                                     @Param(value = "endDeptCode") @NotEmpty String endDeptCode,
                                                                     @Param(value = "departmentCode") @NotEmpty String departmentCode);

    @Query("SELECT e FROM Employee e WHERE e.positionCode > :startDeptCode AND e.positionCode <= :endDeptCode AND e.teamCode = :teamCode")
    List<Employee> findEmployeesByTeamCodeAndPositionCodeRange(@Param(value = "startDeptCode") @NotEmpty String startDeptCode,
                                                                     @Param(value = "endDeptCode") @NotEmpty String endDeptCode,
                                                                     @Param(value = "teamCode") @NotEmpty String teamCode);

    @Query(value =
            "SELECT " +
                    "a.work_policy AS workPolicy, " +
                    "COUNT(a.work_policy) AS policyCount " +
                    "FROM " +
                    "HR_Attendance a " +
                    "JOIN " +
                    "HR_Employee e ON a.employee_id = e.employee_id " +
                    "WHERE " +
                    "e.team_code = :teamCode " +
                    "AND DATE(a.work_start_time) = CURDATE() " +
                    "GROUP BY " +
                    "a.work_policy",
            nativeQuery = true)
    Optional<List<Object[]>> findByMyTeamAttendanceCount(@Param("teamCode") String teamCode);
}
