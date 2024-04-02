package com.example.harbor_employee.Employee.repository;

import com.example.harbor_employee.Employee.domain.Department;
import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.domain.EmployeeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findByDepartmentCode(String departmentCode);
}
