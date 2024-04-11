package com.example.harbor_total.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.harbor_total.Employee.domain.Employee;

import javax.ws.rs.HEAD;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmployeeId(String EmployeeId);
}
