package com.example.harbor_total.commute.controller;

import com.example.harbor_total.commute.dto.req.CommuteListReqDto;
import com.example.harbor_total.commute.service.CommuteService;
import com.example.harbor_total.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/total/commute")
public class CommuteController {

    @Autowired
    private final CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @GetMapping("/attendance")
    public ResponseEntity<CommonResponse> recordAttendance(@RequestHeader String employeeId) {
        String attendanceResult = commuteService.recordAttendance(employeeId);

        if ("이미 출근이 처리되었습니다.".equals(attendanceResult)) {
            // 이미 출근 처리된 경우, 클라이언트에게 충돌을 알리고 메시지 반환
            log.error("이미 출근이 처리되었습니다.");
            return new ResponseEntity<>(new CommonResponse("이미 출근 처리 되었습니다.",attendanceResult), HttpStatus.CONFLICT);
        }
        // 출근 처리가 정상적으로 완료된 경우, 클라이언트에게 성공 메시지 반환
        log.info("출근 처리 되었습니다.");
        return new ResponseEntity<>(new CommonResponse("출근 처리 되었습니다.", attendanceResult), HttpStatus.OK);
    }

    @PostMapping("/Monthlyattendence")
    public ResponseEntity<CommonResponse> MonthlyAttendance(@RequestBody CommuteListReqDto commuteListReqDto,  @RequestHeader("employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("한달 근무리스트", commuteService.MonthlyAttendance(employeeId,commuteListReqDto)), HttpStatus.OK);
    }


    @GetMapping("/leavework")
    public ResponseEntity<CommonResponse> recordLeaveWork(@RequestHeader("employeeId") String employeeId) {
        String leave = commuteService.recordLeaveWork(employeeId);
        return new ResponseEntity<>(new CommonResponse<>("퇴근 처리 되었습니다.", leave), HttpStatus.OK);
    }
}


