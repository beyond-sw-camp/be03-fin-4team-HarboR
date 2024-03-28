package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Notice;
import com.example.harbor_login.Login.dto.NoticeResDto;
import com.example.harbor_login.Login.dto.NoticeUpdateReq;
import com.example.harbor_login.Login.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class NoticeService {
    private final LoginRepository loginRepository;

    public NoticeService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public NoticeResDto Create(NoticeResDto noticeResDto) {
        Notice notice = Notice.builder()


                .build();

    }

    public void update(String employeeId, NoticeUpdateReq noticeUpdateReq, String fileName, String filePath) {
        Notice notice = loginRepository.findByEmployeeId(employeeId).orElseThrow(() ->)
        notice.updateNotice(noticeUpdateReq.getTitle(), noticeUpdateReq.getContents(),
                noticeUpdateReq.getFileName(), noticeUpdateReq.getFilePath());
        loginRepository.save();
    }


}
