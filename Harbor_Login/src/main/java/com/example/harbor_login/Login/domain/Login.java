package com.example.harbor_login.Login.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "HR_Login")
public class Login{

    @Id
    private String email;
    @Column(nullable = false, length = 8)
    private int employeeId;
    @Column(nullable = false, length = 6)
    private String name;
    private String password;
    private String birth;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean delYn = false;
    private Boolean role = false;

    @Builder
    public Login(String email, String name, String password,String birth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
    }

    public static Login createLogin(String email, String password, String name,String birth) {
        return Login.builder()
                .email(email)
                .name(name)
                .password(password)
                .birth(birth)
                .build();
    }
}