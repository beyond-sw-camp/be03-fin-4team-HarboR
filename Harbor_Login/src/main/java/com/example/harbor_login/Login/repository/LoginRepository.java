package com.example.harbor_login.Login.repository;

import com.example.harbor_login.Login.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,String> {
    Optional<Login> findByEmail(String email);

}
