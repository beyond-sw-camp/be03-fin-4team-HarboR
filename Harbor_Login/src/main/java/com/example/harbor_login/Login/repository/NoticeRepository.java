package com.example.harbor_login.Login.repository;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.domain.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NoticeRepository extends JpaRepository<Notice,String> {


        Optional<Notice> findByEmail(String email);

        Optional<Notice> findByNoticeId(int noticeId);

        Page<Notice> findAll(String employeeId, Pageable pageable);

        Page<Notice> findAllByDelYnOrderByCreatedAt(Boolean delYn, Pageable pageable);

        List<Notice> findAll();
    }

