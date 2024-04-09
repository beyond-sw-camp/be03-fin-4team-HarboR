package com.example.harbor_employee.Eworks.repository;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Eworks.domain.Eworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Repository
public interface EworksRepository extends JpaRepository<Eworks, Long> {
    List<Eworks> findAllByEmployee(Employee employee);

    @Query("SELECT e FROM Eworks e WHERE e.firstSignId = :employeeId OR e.secondSignId = :employeeId OR e.thirdSignId = :employeeId")
    List<Eworks> findAllByFirstSignIdOrSecondSignIdOrThirdSignId(@Param(value = "employeeId") @NotEmpty String employeeId);
}
