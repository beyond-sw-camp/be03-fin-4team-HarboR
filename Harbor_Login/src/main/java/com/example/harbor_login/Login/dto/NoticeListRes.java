package com.example.harbor_login.Login.dto;

import com.example.harbor_login.Login.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListRes {

    private String title;

    private String contents;

    private LocalDateTime createdAt;



    public static NoticeListRes mapToNotice(Notice notice) {
        return NoticeListRes.builder()
                .title(notice.getTitle())
                .contents(notice.getContents())
                .build();
    }
}
