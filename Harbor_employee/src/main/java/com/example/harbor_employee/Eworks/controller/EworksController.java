package com.example.harbor_employee.Eworks.controller;

import com.example.harbor_employee.Eworks.dto.request.EworksCreateReqDto;
import com.example.harbor_employee.Eworks.service.EworksService;
import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@Slf4j
@RequestMapping("/employee/eworks")
public class EworksController {
    private final EworksService eworksService;

    public EworksController(EworksService eworksService) {
        this.eworksService = eworksService;
    }

    /**
     * 전자 결재 생성
     * @param eworksCreateReqDto
     * @return
     */
    @PostMapping("/create/{employeeId}")
    public ResponseEntity<HttpStatus> createEworks(@PathVariable("employeeId") String employeeId,
                                                   @RequestBody EworksCreateReqDto eworksCreateReqDto){
        eworksService.create(employeeId, eworksCreateReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    /**
     * 개인 별 전자 결재 리스트 조회 API
     * @return
     */
    @GetMapping("/read/send/{employeeId}")
    public ResponseEntity<CommonResponse> readSendEworksList(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("송신 리스트 출력 완료", eworksService.getSendList(employeeId)), HttpStatus.OK);
    }

    /**
     * 개인 별 전자 결재 리스트 조회 API
     * @return
     */
    @GetMapping("/read/receive/{employeeId}")
    public ResponseEntity<CommonResponse> readReceiveEworksList(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("수신 리스트 출력 완료", eworksService.getReceiveList(employeeId)), HttpStatus.OK);
    }

    /**
     * 승인권자 리스트 출력 API
     * @param departmentCode
     * @return
     */
    @GetMapping("/read/authlist")
    public ResponseEntity<CommonResponse> getAuthList(@RequestParam("departmentCode") String departmentCode){
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", eworksService.getAuthList(departmentCode)), HttpStatus.OK);
    }

    /**
     * 승인/반려 처리 API
     * @param
     * @return
     */
    @PostMapping("/update/{payId}")
    public ResponseEntity<CommonResponse> checkEworks(@PathVariable("payId") String payId, @RequestParam("status") Boolean isApproval){
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", eworksService.updateApproval(payId)), HttpStatus.OK);
    }
}
