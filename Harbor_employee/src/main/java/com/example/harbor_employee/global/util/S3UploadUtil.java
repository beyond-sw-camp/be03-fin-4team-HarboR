package com.example.harbor_employee.global.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3UploadUtil {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷

    // S3 파일 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        // MultipartFile -> File
        File convertFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("file convert error")); // 파일을 변환할 수 없으면 에러

        // S3에 저장할 파일명
        String fileName = dirName + "/" + UUID.randomUUID() + "_" + convertFile.getName();

        // 파일 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());

        // S3에 파일 업로드
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, convertFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)
                .withMetadata(metadata)); // 메타데이터 추가
        String uploadImageUrl = amazonS3Client.getUrl(bucket, fileName).toString();

        // 로컬 파일 삭제
        convertFile.delete();

        return uploadImageUrl;
    }

    public ResponseEntity<byte[]> downloadFileBlob(String storedFileName, HttpServletRequest request) {
        System.out.println("storedFileName = " + "images/" + storedFileName);
        try {
            S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, "profile/" + storedFileName));
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(objectInputStream);

            String encodedFilename = makeFileName(request, URLEncoder.encode(storedFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(s3Object.getObjectMetadata().getContentType())); // Set the content type based on the file
            headers.setContentLength(bytes.length);
            headers.setContentDispositionFormData("attachment", encodedFilename);

            // 파일을 스트리밍 방식으로 응답
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            throw new IllegalArgumentException();
        }
    }

    public boolean download(String fileKey, String downloadFileName, HttpServletRequest request, HttpServletResponse response) {
        if (fileKey == null) {
            return false;
        }
        S3Object fullObject = null;
        try {
            fullObject = amazonS3Client.getObject(new GetObjectRequest(bucket, "profile/" + fileKey));
            if (fullObject == null) {
                return false;
            }
        } catch (AmazonS3Exception e) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        OutputStream os = null;
        FileInputStream fis = null;
        boolean success = false;
        try {
            S3ObjectInputStream objectInputStream = fullObject.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(objectInputStream);
            String fileName = null;
            if(downloadFileName !=null){
                fileName = URLDecoder.decode(downloadFileName,"UTF-8").replaceAll("\\+", "%20");
            } else{
                fileName = makeFileName(request, fileKey); // URL Encoder.encode(fileKey,"UTF-8").replaceAll("\\+", "%20");
            }
            response.setContentType(String.valueOf(MediaType.parseMediaType(fullObject.getObjectMetadata().getContentType())));
            response.setHeader("Content-Disposition", "binary;");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            FileCopyUtils.copy(bytes,response.getOutputStream());
            success=true;
        } catch (IOException e) {
            log.debug(e.getMessage(),e);
        } finally{
            try{
                if(fis!=null){
                    fis.close();
                }
            }catch(IOException e){
                log.debug(e.getMessage(),e);
            }
        }
        return success;
    }

    /**
     * 파일명이 한글인 경우 URL encode이 필요함.
     * @param request
     * @param displayFileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private String makeFileName(HttpServletRequest request, String displayFileName) throws UnsupportedEncodingException {
        String header = request.getHeader("User-Agent");
        String encodedFilename = null;
        if (header.contains("MSIE")) {
            encodedFilename = URLEncoder.encode(displayFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        } else if (header.contains("Trident")) {
            encodedFilename = URLEncoder.encode(displayFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        } else if (header.contains("Chrome")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < displayFileName.length(); i++) {
                char c = displayFileName.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, StandardCharsets.UTF_8));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else if (header.contains("Opera")) {
            encodedFilename = "\"" + new String(displayFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
        } else if (header.contains("Safari")) {
            encodedFilename = URLDecoder.decode("\"" + new String(displayFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"", StandardCharsets.UTF_8);
        } else {
            encodedFilename = URLDecoder.decode("\"" + new String(displayFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"", StandardCharsets.UTF_8);
        }
        return encodedFilename;
    }

    // S3 파일 삭제
    public void delete(String path) {
        amazonS3Client.deleteObject(bucket, "profile/" + path);
    }

    // 파일 convert 후 로컬에 업로드
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.home") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}