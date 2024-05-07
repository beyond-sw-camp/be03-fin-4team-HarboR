package com.example.harbor_login.Notice.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Column
    private String fileName;

    @Column
    private String filePath;


    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private boolean delYn= false; // notice 삭제 유무



    public void updateNotice(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
    public void deleteNotice(){ // notice 삭제 시 호출
        this.delYn = true;
    }
    public void setImagePath(String filePath){
        this.filePath = filePath;


    }
}