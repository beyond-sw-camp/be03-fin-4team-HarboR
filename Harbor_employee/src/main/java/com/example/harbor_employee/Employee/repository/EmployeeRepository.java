package com.example.harbor_employee.Employee.repository;

import com.example.harbor_employee.Employee.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmployeeId(String employeeId);
    Page<Employee> findAll(Specification<Employee> specification, Pageable pageable);
    List<Employee> findAllByDepartmentCode(String departmentCode);
}
