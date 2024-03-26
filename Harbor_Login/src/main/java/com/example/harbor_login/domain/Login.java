package com.example.harbor_login.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HR_Login")
public class Login {
    @Id
    @Column(nullable = false, length = 20)
    private String email;
    @Column(nullable = false, length = 8)
    private int employeeId;
    @Column(nullable = false, length = 10)
    private String name;
    @Column(nullable = false, length = 20)
    private String password;
    private boolean delYn;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
