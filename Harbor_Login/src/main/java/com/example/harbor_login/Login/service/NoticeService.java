package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Notice;
import com.example.harbor_login.Login.dto.NoticeCreateReqDto;
import com.example.harbor_login.Login.dto.NoticeListRes;
import com.example.harbor_login.Login.dto.NoticeUpdateReq;
import com.example.harbor_login.Login.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    public void NoticeCreate(NoticeCreateReqDto noticeCreateReqDto) {
        Notice notice = Notice.builder()
                .title(noticeCreateReqDto.getTitle()) // NoticeResDto에서 title을 가져와서 설정
                .contents(noticeCreateReqDto.getContents())
                .fileName(noticeCreateReqDto.getFileName())
                .filePath(noticeCreateReqDto.getFilePath())// NoticeResDto에서 content를 가져와서 설정
                // 다른 필드들도 필요에 따라 추가할 수 있음
                .build();

        noticeRepository.save(notice);


    }

    public void NoticeUpdate(int noticeId, NoticeUpdateReq noticeUpdateReq) {

        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사원입니다."));
        notice.updateNotice(noticeUpdateReq.getTitle(), noticeUpdateReq.getContents(),
                noticeUpdateReq.getFileName(), noticeUpdateReq.getFilePath());
        noticeRepository.save(notice);
    }

    public void NoticeDelete(int noticeId) {

        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사원입니다."));
        notice.deleteNotice();
        noticeRepository.save(notice);
    }


    public List<NoticeListRes> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListRes> noticeListRes = new ArrayList<>();
        for (Notice notice : notices){
            NoticeListRes noticeListRes1= new NoticeListRes(notice.getNoticeId(),notice.getTitle(),notice.getContents(),
                    notice.getTitle(),notice.getFilePath(),notice.getFileName(),notice.getCreatedAt());

        }return noticeListRes;
    }

    public Page<Notice> findAllPage(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices;
    }

    public Notice findById(int noticeId)throws EntityNotFoundException{
        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new EntityNotFoundException("검색하신 ID의 Notice가 없습니다."));
        return notice;

    }
}
