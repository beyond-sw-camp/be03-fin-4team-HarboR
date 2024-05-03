package com.example.harbor_login.Notice.service;

import com.example.harbor_login.Notice.domain.Notice;
import com.example.harbor_login.Notice.dto.request.NoticeCreateReqDto;
import com.example.harbor_login.Notice.dto.request.NoticeUpdateReq;
import com.example.harbor_login.Notice.dto.response.NoticeDetailRes;
import com.example.harbor_login.Notice.dto.response.NoticeListRes;
import com.example.harbor_login.Notice.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@Transactional
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    public Notice NoticeCreate(NoticeCreateReqDto noticeCreateReqDto) {
        MultipartFile multipartFile = noticeCreateReqDto.getFilePath();
        String fileName = multipartFile.getOriginalFilename();

        Notice new_notice = Notice.builder()
                .title(noticeCreateReqDto.getTitle()) // NoticeResDto에서 title을 가져와서 설정
                .contents(noticeCreateReqDto.getContents())
                .fileName(noticeCreateReqDto.getFileName())
                .createdAt(noticeCreateReqDto.getCreatedAt())
                // NoticeResDto에서 content를 가져와서 설정
                // 다른 필드들도 필요에 따라 추가할 수 있음
                .build();
        Notice notice = noticeRepository.save(new_notice);
        Path path = Paths.get("/Users/wingk/Desktop/final", notice.getNoticeId() + "_" + fileName);
        notice.setImagePath(path.toString());
        try {
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new IllegalArgumentException("image is not available");
        }
        return notice;
    }
    public Notice NoticeUpdate(int noticeId, NoticeUpdateReq noticeUpdateReq) {
        MultipartFile multipartFile = noticeUpdateReq.getFilePath();
        String fileName = multipartFile.getOriginalFilename();

        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 글입니다."));
        notice.updateNotice(noticeUpdateReq.getTitle(), noticeUpdateReq.getContents(),
                noticeUpdateReq.getFileName());
        Notice notice1 = noticeRepository.save(notice);
        Path path = Paths.get("/Users/wingk/Desktop/final", notice1.getNoticeId() + "_" + fileName);
        notice1.setImagePath(path.toString());
        try {
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new IllegalArgumentException("image is not available");
        }
        return notice1;
    }

    public void NoticeDelete(int noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사원입니다."));
        notice.deleteNotice();
        noticeRepository.save(notice);
    }


    public Page<NoticeListRes> findAll(Pageable pageable) {
        return noticeRepository.findAllByDelYnOrderByCreatedAt(false,  pageable).map(NoticeListRes::mapToNotice);

    }

//
//    public Page<Notice> findAllPage(Pageable pageable) {
////        Page<Notice> notices = noticeRepository.findAll(pageable);
////        return notices;
//        return null;


    public Notice findById(int noticeId) throws EntityNotFoundException {
            Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(() -> new EntityNotFoundException("검색하신 ID의 Notice가 없습니다."));
        return notice;

    }
    public NoticeDetailRes NoticeDetail(int noticeId) {

        Notice notice =noticeRepository.findByNoticeId(noticeId).orElseThrow(()-> new EntityNotFoundException("검색하신 ID 의 게시글이 없습니다"));
        NoticeDetailRes noticeDetailRes = new NoticeDetailRes();
        noticeDetailRes.setTitle(notice.getTitle());
        noticeDetailRes.setContents(notice.getContents());
        noticeDetailRes.setCreatedAt(notice.getCreatedAt());

        return noticeDetailRes;
    }
}

