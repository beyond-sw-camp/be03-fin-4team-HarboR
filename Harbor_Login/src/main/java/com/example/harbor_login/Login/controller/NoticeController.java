package com.example.harbor_login.Login.controller;

import com.example.harbor_login.Login.domain.Notice;
import com.example.harbor_login.Login.dto.NoticeCreateReqDto;
import com.example.harbor_login.Login.dto.NoticeDetailRes;
import com.example.harbor_login.Login.dto.NoticeListRes;
import com.example.harbor_login.Login.dto.NoticeUpdateReq;
import com.example.harbor_login.Login.service.NoticeService;
import com.example.harbor_login.global.common.CommonResponse;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
        Page<NoticeListRes> notices = noticeService.findAll(pageable);
        System.out.println("ok");
        return new ResponseEntity<>(new CommonResponse("공지사항 입니다", notices), HttpStatus.OK);


    }
    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> NoticeCreate(NoticeCreateReqDto noticeCreateReqDto){
        Notice notice = noticeService.NoticeCreate(noticeCreateReqDto);
        return new ResponseEntity<>(new CommonResponse("크레이트", notice), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")

    @PatchMapping("/update/{noticeId}")
    public ResponseEntity<CommonResponse> updateNotice(@PathVariable("noticeId") int noticeId, NoticeUpdateReq noticeUpdateReq) {

        noticeService.NoticeUpdate(noticeId, noticeUpdateReq);
        return new ResponseEntity<>(new CommonResponse("업데이트", noticeId), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<CommonResponse> deleteNotice(@PathVariable("noticeId") int noticeId) {
        System.out.println("2"+noticeId);
        noticeService.NoticeDelete(noticeId);
        System.out.println("1"+noticeId);
        return new ResponseEntity<>(new CommonResponse("member successfully delete", noticeId), HttpStatus.OK);


    }
    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<CommonResponse> detailNotice(@PathVariable("noticeId") int noticeId){
        NoticeDetailRes noticeDetailRes = noticeService.NoticeDetail(noticeId);
        return new ResponseEntity<>(new CommonResponse("this page is detail",noticeDetailRes) , HttpStatus.OK);

    }


}


