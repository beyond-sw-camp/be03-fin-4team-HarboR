package com.example.harbor_login.Notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDetailRes {


    private String title;

    private String contents;

    private String filePath;

    private LocalDateTime createdAt;

}
