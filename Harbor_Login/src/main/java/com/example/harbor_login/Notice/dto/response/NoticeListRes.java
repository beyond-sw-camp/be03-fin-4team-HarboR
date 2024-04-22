package com.example.harbor_login.Notice.dto.response;

import com.example.harbor_login.Notice.domain.Notice;
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
