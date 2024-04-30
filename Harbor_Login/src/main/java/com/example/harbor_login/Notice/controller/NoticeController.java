package com.example.harbor_login.Notice.controller;

import com.ctc.wstx.util.StringUtil;
import com.example.harbor_login.Notice.dto.request.NoticeCreateReqDto;
import com.example.harbor_login.Notice.dto.request.NoticeUpdateReq;
import com.example.harbor_login.Notice.service.NoticeService;
import com.example.harbor_login.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


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

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse> NoticeCreate(@RequestPart NoticeCreateReqDto request,
                                                       @RequestPart(name = "file", required = false) MultipartFile multipartFile) {
        System.out.println(request);
        noticeService.NoticeCreate(request, multipartFile);
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
    @PostMapping("/download/{fileName}")
    public void download(HttpServletResponse response, @PathVariable("fileName") String fileName) throws Exception {
        System.out.println("fileName = " + fileName);
        try {
            String path = "C:\\Users\\Playdata\\Desktop\\final\\"+fileName; // 경로에 접근할 때 역슬래시('\') 사용
            System.out.println("path = " + path);
            File file = new File(path);
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
            OutputStream out = response.getOutputStream();
            System.out.println("out = " + out);
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                out.write(buffer, 0, read);
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }
}
