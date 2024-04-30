package com.example.harbor_login.Login.repository;

import com.example.harbor_login.Login.domain.Login;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,String> {
    Optional<Login> findByEmail(String email);
    Optional<Login> findByEmployeeId(String employeeId);


    Optional<Login> findByEmailAndName(String email, String name);




    @Query("SELECT MAX(l.employeeId) FROM Login l")
    String findMaxEmployeeNumber();

    Page<Login> findAllByDelYnNotOrderByCreatedAt(Boolean delYn, Pageable pageable);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Login l SET l.password = :password WHERE l.employeeId = :employeeId")
//    void updatePw(String password, String employeeId);

//    @Modifying
//    @Query("UPDATE User u SET u.password = :password WHERE u.indexId = :indexId")
//    void updatePw(@Param("password") String password, @Param("indexId") Long indexId);
}



