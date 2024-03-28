package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Notice;
import com.example.harbor_login.Login.dto.NoticeResDto;
import com.example.harbor_login.Login.dto.NoticeUpdateReq;
import com.example.harbor_login.Login.repository.LoginRepository;
import com.example.harbor_login.Login.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    public NoticeResDto Create(NoticeResDto noticeResDto) {
        Notice notice = Notice.builder()


                .build();

    }

    public void NoticeUpdate(String employeeId, NoticeUpdateReq noticeUpdateReq, String fileName, String filePath) {

        Notice notice = noticeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사원입니다."));
        notice.updateNotice(noticeUpdateReq.getTitle(), noticeUpdateReq.getContents(),
                noticeUpdateReq.getFileName(), noticeUpdateReq.getFilePath());
        noticeRepository.save(notice);
    }

    public void NoticeDelete(String employeeId) {

        Notice notice = noticeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사원입니다."));
        notice.d
    }
}
