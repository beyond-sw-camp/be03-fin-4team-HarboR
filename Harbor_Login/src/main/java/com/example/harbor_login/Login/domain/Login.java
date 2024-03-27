package com.example.harbor_login.Login.domain;

import com.example.harbor_login.global.support.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "HR_Login")
public class Login{

    @Id
    private String email;
    private String employeeId;
    @Column(nullable = false, length = 6)
    private String name;
    private String password;
    private String birth;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean delYn = false;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Login(String email, String name, String password,String birth, Role role, String employeeId, boolean delYn, LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.role = role;
        this.employeeId = employeeId;
        this.delYn = delYn;
        this.createdAt = createdAt;
    }

    public static Login createLogin(String email, String password, String name,String birth) {
        return Login.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER)
                .birth(birth)
                .build();
    }

    public void UpdateDelYn() {
        this.delYn = true;
    }

    public void UpdateEmployeeId(String employeeId) {
        this.employeeId = employeeId;}
}