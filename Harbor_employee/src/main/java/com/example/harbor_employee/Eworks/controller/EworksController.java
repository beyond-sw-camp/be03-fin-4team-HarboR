package com.example.harbor_employee.Eworks.controller;

import com.example.harbor_employee.Eworks.dto.EworksCreateReqDto;
import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/employee/eworks")
public class EworksController {
    @PostMapping("/create")
    public static ResponseEntity<CommonResponse> createEworks(@RequestBody EworksCreateReqDto eworksCreateReqDto){
        return new ResponseEntity<>(new CommonResponse("결재 요청 완료", ""), HttpStatus.CREATED);
    }

}
