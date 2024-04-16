package com.example.harbor_employee.schedule.controller;

import com.example.harbor_employee.global.common.CommonResponse;
import com.example.harbor_employee.schedule.domain.Schedule;
import com.example.harbor_employee.schedule.dto.ScheduleCreateReq;
import com.example.harbor_employee.schedule.dto.ScheduleListRes;
import com.example.harbor_employee.schedule.dto.ScheduleUpdateReq;
import com.example.harbor_employee.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;

    }

    @GetMapping("/list")
    public ResponseEntity<CommonResponse> ScheduleList(ScheduleListRes scheduleListRes) {
        List<ScheduleListRes> scheduleListRes1 = scheduleService.findAllSchedule();
        return new ResponseEntity<>(new CommonResponse("스케줄 리스트입니다", scheduleListRes1), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> ScheduleCreate(@RequestBody ScheduleCreateReq scheduleCreateReq) {
        Schedule schedule = scheduleService.CreateSchedule(scheduleCreateReq);
        return new ResponseEntity<>(new CommonResponse("스케줄이 생성되었습니다.", schedule), HttpStatus.CREATED);

    }

    @PatchMapping("/update/{scheduleId}")
    public ResponseEntity<CommonResponse> ScheduleUpdate(@PathVariable("scheduleId") Long scheduleId, @RequestBody ScheduleUpdateReq scheduleUpdateReq) {
        scheduleService.scheduleUpdate(scheduleId, scheduleUpdateReq);
        return new ResponseEntity<>(new CommonResponse("업데이트 되었습니다", scheduleId), HttpStatus.OK);

    }

    @DeleteMapping("delete/{scheduleId}")
    public ResponseEntity<CommonResponse> ScheduleDelete(@PathVariable("scheduleId") Long scheduleId) {
        scheduleService.ScheduleDelete(scheduleId);
        return new ResponseEntity<>(new CommonResponse("삭제되었습니다.", scheduleId), HttpStatus.OK);

    }

    @GetMapping("detail/{scheduleId}")
    public ResponseEntity<CommonResponse> ScheduleDetail(@PathVariable("scheduleId") Long scheduleId){
        Schedule schedule = scheduleService.scheduleDetail(scheduleId);
        return new ResponseEntity<>(new CommonResponse("디테일입니다.", schedule), HttpStatus.OK);


    }
}
