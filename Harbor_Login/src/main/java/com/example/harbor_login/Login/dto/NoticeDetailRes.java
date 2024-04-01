package com.example.harbor_login.Login.dto;

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

    private LocalDateTime createdAt;

}
