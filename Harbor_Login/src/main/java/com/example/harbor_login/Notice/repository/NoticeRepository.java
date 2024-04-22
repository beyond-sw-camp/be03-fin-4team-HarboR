package com.example.harbor_login.Notice.repository;

import com.example.harbor_login.Notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NoticeRepository extends JpaRepository<Notice,String> {


//    Optional<Notice> findByEmail(String email);

    Optional<Notice> findByNoticeId(int noticeId);
    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findAllByDelYnOrderByCreatedAt(Boolean delYn, Pageable pageable);


}

