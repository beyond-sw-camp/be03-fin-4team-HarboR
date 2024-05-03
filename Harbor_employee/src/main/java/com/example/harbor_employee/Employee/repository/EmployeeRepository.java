package com.example.harbor_employee.Employee.repository;

import com.example.harbor_employee.Employee.domain.Employee;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmployeeId(String employeeId);
    Page<Employee> findAll(Specification<T> specification, Pageable pageable);
    List<Employee> findAllByDepartmentCode(String departmentCode);

    @Query("SELECT e FROM Employee e WHERE e.positionCode > :startDeptCode AND e.positionCode <= :endDeptCode AND e.departmentCode = :departmentCode")
    List<Employee> findEmployeesByDepartmentCodeAndPositionCodeRange(@Param(value = "startDeptCode") @NotEmpty String startDeptCode,
                                                                     @Param(value = "endDeptCode") @NotEmpty String endDeptCode,
                                                                     @Param(value = "departmentCode") @NotEmpty String departmentCode);
}
