package com.example.harbor_login.Notice.controller;

import com.example.harbor_login.Notice.dto.request.NoticeCreateReqDto;
import com.example.harbor_login.Notice.dto.request.NoticeUpdateReq;
import com.example.harbor_login.Notice.service.NoticeService;
import com.example.harbor_login.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public ResponseEntity<CommonResponse> NoticeList(Pageable pageable) {
        return new ResponseEntity<>(new CommonResponse("공지사항 리스트 출력", noticeService.findAll(pageable)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CommonResponse> NoticeCreate(NoticeCreateReqDto noticeCreateReqDto){
        noticeService.NoticeCreate(noticeCreateReqDto);
        return new ResponseEntity<>(new CommonResponse("공지사항 생성 성공", ""), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{noticeId}")
    public ResponseEntity<CommonResponse> updateNotice(@PathVariable("noticeId") int noticeId, NoticeUpdateReq noticeUpdateReq) {
        noticeService.NoticeUpdate(noticeId, noticeUpdateReq);
        return new ResponseEntity<>(new CommonResponse("공지사항 수정 성공", ""), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<CommonResponse> deleteNotice(@PathVariable("noticeId") int noticeId) {
        noticeService.NoticeDelete(noticeId);
        return new ResponseEntity<>(new CommonResponse("공지사항 삭제 성공", noticeId), HttpStatus.OK);
    }

    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<CommonResponse> detailNotice(@PathVariable("noticeId") int noticeId){
        return new ResponseEntity<>(new CommonResponse("공지사항 상세 페이지", noticeService.NoticeDetail(noticeId)) , HttpStatus.OK);
    }


}


