package com.example.harbor_login.Notice.controller;

import com.example.harbor_login.Notice.dto.request.NoticeCreateReqDto;
import com.example.harbor_login.Notice.dto.request.NoticeUpdateReq;
import com.example.harbor_login.Notice.service.NoticeService;
import com.example.harbor_login.global.common.CommonResponse;
import com.example.harbor_login.global.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/notice")
public class NoticeController {
    private final S3UploadUtil s3UploadUtil;
    private final NoticeService noticeService;

    @GetMapping("/list")
    public ResponseEntity<CommonResponse> NoticeList(Pageable pageable) {
        return new ResponseEntity<>(new CommonResponse("공지사항 리스트 출력", noticeService.findAll(pageable)), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse> NoticeCreate(@RequestPart NoticeCreateReqDto request,
                                                       @RequestPart(name = "file", required = false) MultipartFile multipartFile) throws IOException {
        System.out.println(request);
        noticeService.NoticeCreate(request, multipartFile);
        return new ResponseEntity<>(new CommonResponse("공지사항 생성 성공", ""), HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{noticeId}")
    public ResponseEntity<CommonResponse> updateNotice(@PathVariable("noticeId") int noticeId,
                                                       @RequestPart NoticeUpdateReq request,
                                                       @RequestPart(name = "file", required = false) MultipartFile multipartFile) throws IOException{
        noticeService.NoticeUpdate(noticeId, request, multipartFile);
        return new ResponseEntity<>(new CommonResponse("공지사항 수정 성공", ""), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<CommonResponse> deleteNotice(@PathVariable("noticeId") int noticeId) {
        noticeService.NoticeDelete(noticeId);
        return new ResponseEntity<>(new CommonResponse("공지사항 삭제 성공", noticeId), HttpStatus.OK);
    }

    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<CommonResponse> detailNotice(@PathVariable("noticeId") int noticeId) {
        return new ResponseEntity<>(new CommonResponse("공지사항 상세 페이지", noticeService.NoticeDetail(noticeId)), HttpStatus.OK);
    }

    //    @GetMapping("/download/{fileName}")
//    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException{
//        Resource resource = noticeService.loadFile(fileName);
//        String absolutePath = resource.getFile().getAbsolutePath();
//        String contentType = request.getServletContext().getMimeType(absolutePath);
//        if (!StringUtils.hasText(contentType)) contentType = "application/octet-stream";
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//
//    }
//    C:\Users\Playdata\Desktop\final\36_demo (2).zip
    @GetMapping("/download/{fileKey}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileKey,
                                          @RequestParam(required = false) String downloadFileName,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        // 서비스 메소드를 호출합니다.
        boolean success = s3UploadUtil.download(fileKey, downloadFileName, request, response);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
