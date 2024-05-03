package com.example.harbor_login.Notice.service;

import com.example.harbor_login.Notice.domain.Notice;
import com.example.harbor_login.Notice.dto.request.NoticeCreateReqDto;
import com.example.harbor_login.Notice.dto.request.NoticeUpdateReq;
import com.example.harbor_login.Notice.dto.response.NoticeDetailRes;
import com.example.harbor_login.Notice.dto.response.NoticeListRes;
import com.example.harbor_login.Notice.repository.NoticeRepository;
import com.example.harbor_login.global.util.S3UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@Transactional
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final S3UploadUtil s3UploadUtil;

    public NoticeService(NoticeRepository noticeRepository, S3UploadUtil s3UploadUtil) {
        this.noticeRepository = noticeRepository;
        this.s3UploadUtil = s3UploadUtil;
    }
//    private final Path fileStorageLocation;

//    public NoticeService(NoticeRepository noticeRepository, Path fileStorageLocation) {
//        this.noticeRepository = noticeRepository;
//        this.fileStorageLocation = Paths.get(fileUp);
//    }


    public Notice NoticeCreate(NoticeCreateReqDto noticeCreateReqDto, MultipartFile multipartFile) throws IOException {
        MultipartFile multipartFile1= noticeCreateReqDto.getFilePath();
        String fileName = multipartFile.getOriginalFilename();

        Notice new_notice = Notice.builder()
                .title(noticeCreateReqDto.getTitle()) // NoticeResDto에서 title을 가져와서 설정
                .contents(noticeCreateReqDto.getContents())
//                .fileName(noticeCreateReqDto.getFileName())
//                .createdAt(noticeCreateReqDto.getCreatedAt())
                // NoticeResDto에서 content를 가져와서 설정
                // 다른 필드들도 필요에 따라 추가할 수 있음
                .build();
        Notice notice = noticeRepository.save(new_notice);
        String uploadUrl = s3UploadUtil.upload(multipartFile, "images");
        notice.setImagePath(uploadUrl);
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
        noticeDetailRes.setFilePath(notice.getFilePath());
        noticeDetailRes.setCreatedAt(notice.getCreatedAt());

        return noticeDetailRes;
    }
//    public Resource loadFile(String fileName){
//        try {
//            Path filePath = fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if(resource.exists()){
//                return  resource;
//
//            }else {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//            }
//
//        }catch (MalformedURLException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//        }
//    }
}

