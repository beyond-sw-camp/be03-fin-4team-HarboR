package com.example.harbor_login.Login.repository;

import com.example.harbor_login.Login.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,String> {
    Optional<Login> findByEmail(String email);
    Optional<Login> findByEmployeeId(String employeeId);
    @Query("SELECT MAX(l.employeeId) FROM Login l")
    String findMaxEmployeeNumber();
}
