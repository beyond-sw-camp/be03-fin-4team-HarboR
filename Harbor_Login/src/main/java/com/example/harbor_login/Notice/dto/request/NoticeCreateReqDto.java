package com.example.harbor_login.Notice.dto.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class NoticeCreateReqDto {


    private String title;

    private String contents;

    private String fileName;

    private MultipartFile filePath;
}
