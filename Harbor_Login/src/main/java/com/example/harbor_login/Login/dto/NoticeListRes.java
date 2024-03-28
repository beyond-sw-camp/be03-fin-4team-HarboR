package com.example.harbor_login.Login.dto;

import com.example.harbor_login.Login.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListRes {
    private int noticeId;

    private String title;

    private String contents;

    private String fileName;

    private String filePath;

    private LocalDateTime createdAt;

    public NoticeListRes(int noticeId, String title, String contents, String title1, String filePath, String fileName, LocalDateTime createdAt) {
    }

    public static NoticeListRes mapToNotice(Notice notice) {
        return NoticeListRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .fileName(notice.getFileName())
                .filePath(notice.getFilePath())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
