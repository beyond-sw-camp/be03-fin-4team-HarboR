package com.example.harbor_total.Annual.controller;

import com.example.harbor_total.Annual.dto.request.ApprovalReqDto;
import com.example.harbor_total.Annual.service.AnnualService;
import com.example.harbor_total.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/total/annual")
public class AnnualController {
    private final AnnualService annualService;

    public AnnualController(AnnualService annualService) {
        this.annualService = annualService;
    }

//    todo: 휴가 신청, 승인, 승인 할 거 리스트,
//
//    @GetMapping("/list")
//    public ResponseEntity<CommonResponse> getlist() {
//
//    }

    /**
     * 개인 별 요청 전자 결재 리스트 조회 API
     * @return
     */
    @GetMapping("/read/send")
    public ResponseEntity<CommonResponse> readSendEworksList(@RequestHeader("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("요청 리스트 출력 완료", annualService.getSendList(employeeId)), HttpStatus.OK);
    }

    /**
     * 개인 별 수신 전자 결재 리스트 조회 API
     * @return
     */
    @GetMapping("/read/receive")
    public ResponseEntity<CommonResponse> readReceiveEworksList(@RequestHeader("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("수신 리스트 출력 완료", annualService.getReceiveList(employeeId)), HttpStatus.OK);
    }

    /**
     * 승인권자 리스트 출력 API
     * @param employeeId
     * @return
     */
    @GetMapping("/read/authlist")
    public ResponseEntity<CommonResponse> getAuthList(@RequestHeader("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("승인권자 리스트 출력 완료", annualService.getAuthList(employeeId)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{annualId}")
    public HttpStatus deleteAnnual(@PathVariable("annualId") Long annualId){
        return annualService.deleteAnnual(annualId);
    }

    /**
     * 승인/반려 처리 API
     * @param approvalReqDto
     * @return
     */
    @PostMapping("/approval")
    public ResponseEntity<CommonResponse> approvalEworks(@RequestHeader("employeeId") String employeeId, @RequestBody ApprovalReqDto approvalReqDto){
        annualService.updateApproval(employeeId, approvalReqDto);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", null), HttpStatus.OK);
    }
}
