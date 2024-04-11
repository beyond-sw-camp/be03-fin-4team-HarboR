package com.example.harbor_total.Annual.controller;


import com.example.harbor_total.Annual.service.AnnualService;
import com.example.harbor_total.global.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
