package com.example.harbor_login.Login.controller;

import com.example.harbor_login.Login.domain.Notice;
import com.example.harbor_login.Login.dto.NoticeUpdateReq;
import com.example.harbor_login.Login.service.NoticeService;
import com.example.harbor_login.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> getUserList(@RequestParam(value = "noticeId") int noticeId, Pageable pageable) {

        return new ResponseEntity.PagedResponse<SignInResponse>(memberService.findAll(noticeId, pageable));
    }

    @PatchMapping("/update/{noticeId}")
    public ResponseEntity<CommonResponse> updateNotice(@PathVariable(value = "noticeId") int noticeId,
                                                       @RequestBody NoticeUpdateReq noticeUpdateReq) {
        noticeService.NoticeUpdate(noticeId, noticeUpdateReq);
        return new ResponseEntity<>(new CommonResponse("member successfully created", noticeUpdateReq.getNoticeId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<CommonResponse> deleteNotice(@PathVariable(value = "noticeId") int noticeId, @RequestBody NoticeUpdateReq noticeUpdateReq) {
        noticeService.NoticeDelete(noticeId);
        return new ResponseEntity<>(new CommonResponse("member successfully created", noticeUpdateReq.getNoticeId()), HttpStatus.OK);


    }


}


