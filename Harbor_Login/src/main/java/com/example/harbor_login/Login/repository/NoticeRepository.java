package com.example.harbor_login.Login.repository;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NoticeRepository extends JpaRepository<Notice,String> {


        Optional<Notice> findByEmail(String email);

        Optional<Notice> findByEmployeeId(String employeeId);

    }

