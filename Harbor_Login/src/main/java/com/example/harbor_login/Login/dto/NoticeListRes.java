package com.example.harbor_login.Login.dto;

import com.example.harbor_login.Login.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListRes {

    private int noticeId;

    private String title;

    private String contents;

    private LocalDate createdAt;




    public static NoticeListRes mapToNotice(Notice notice) {
        return NoticeListRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .createdAt(LocalDate.from(notice.getCreatedAt()))
                .build();
    }
}
