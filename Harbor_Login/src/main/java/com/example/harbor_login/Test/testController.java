package com.example.harbor_login.Test;

import com.example.harbor_login.common.CommonResponse;
import com.example.harbor_login.dto.MemberCreateReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login/member")
public class testController {
    @PostMapping("/test")
    public ResponseEntity<CommonResponse> test(@Valid @RequestBody MemberCreateReqDto memberCreateReqDto) {
        return new ResponseEntity<>(new CommonResponse("member successfully created",memberCreateReqDto.getEmail(),HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
