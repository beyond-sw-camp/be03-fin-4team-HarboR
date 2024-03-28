package com.example.harbor_login.Login.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class NoticeCreateReqDto {

    private int noticeId;

    private String title;

    private String contents;

    private String fileName;

    private String filePath;

    private LocalDateTime createdAt;
}
