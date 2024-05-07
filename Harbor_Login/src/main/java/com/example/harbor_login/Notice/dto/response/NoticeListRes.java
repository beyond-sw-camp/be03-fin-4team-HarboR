package com.example.harbor_login.Notice.dto.response;

import com.example.harbor_login.Notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListRes {

    private int noticeId;
    private String title;
    private String contents;
    private LocalDate createdAt;
    private String filePath;

    public static NoticeListRes mapToNotice(Notice notice) {
        String path = "";
        if(!notice.getFilePath().isBlank()) path = notice.getFilePath().split("images/")[1];
        return NoticeListRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .createdAt(LocalDate.from(notice.getCreatedAt()))
                .filePath(path)
                .build();
    }
}
