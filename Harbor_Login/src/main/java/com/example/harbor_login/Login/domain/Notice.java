package com.example.harbor_login.Login.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HR_Notice")

public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 3000)
    private String contents;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;


    @CreatedDate
    private LocalDateTime createdAt;

    private String delYn="N"; // notice 삭제 유무



    public void updateNotice(String title, String contents, String fileName, String filePath){

        this.title = title;
        this.contents = contents;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    public void deleteNotice(){ // notice 삭제 시 호출
        this.delYn = "Y";
    }
}