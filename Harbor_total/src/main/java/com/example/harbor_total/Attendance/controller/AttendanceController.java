package com.example.harbor_total.Attendance.controller;

import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import com.example.harbor_total.Attendance.service.AttendanceService;
import com.example.harbor_total.Attendance.dto.EmployeeStatusDto;
import com.example.harbor_total.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@Slf4j
public class AttendanceController {
    private final AttendanceService commuteService;

    public AttendanceController(AttendanceService commuteService) {
        this.commuteService = commuteService;
    }

    /**
     *
     * @param attendanceFlexibleWorkReqDto
     * @description 시작시간, 끝나는시간, 코드번호별로 나누어짐
     * @description 시차근무제, 휴가, 출장 신청
     * @return AttendanceListResDto
     * */
    @PostMapping("/requestwork")
    public ResponseEntity<CommonResponse> requestwork(@RequestBody AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, @RequestHeader("employeeId") String employeeId) {
        log.info("attendanceFlexibleWorkReqDto : {} ", attendanceFlexibleWorkReqDto);
        return new ResponseEntity<>(new CommonResponse("시차 근무제 신청하기",commuteService.requestwork(attendanceFlexibleWorkReqDto,employeeId)), HttpStatus.OK);
    }

    //    근무 리스트
    @GetMapping("/groupbyteamlist")
    public ResponseEntity<CommonResponse> getList(@RequestHeader("employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("당일 근무 리스트 가져오기", commuteService.getListGroupByTeam(employeeId)), HttpStatus.OK);
    }

    @GetMapping("/status")
    public List<EmployeeStatusDto> getStatus(@RequestParam("id") List<String> employeeId){
        return commuteService.getEmployeeStatus(employeeId);
    }
}
