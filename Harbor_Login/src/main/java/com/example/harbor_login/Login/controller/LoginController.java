package com.example.harbor_login.Login.controller;


import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.JoinRequest;
import com.example.harbor_login.Login.dto.LoginSignInReqDto;
import com.example.harbor_login.Login.dto.LoginSignUpReqDto;
import com.example.harbor_login.Login.service.EmailService;
import com.example.harbor_login.Login.service.LoginService;
import com.example.harbor_login.common.CommonResponse;
import com.example.harbor_login.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login/account")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;


    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody LoginSignUpReqDto loginSignUpReqDto, BindingResult bindingResult) throws BindException {

        System.out.println("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        loginService.signup(loginSignUpReqDto);

        return new ResponseEntity<>(new CommonResponse("member successfully created",loginSignUpReqDto.getEmail()), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse> signin(@Valid @RequestBody LoginSignInReqDto loginSignInReqDto, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Login member = loginService.signin(loginSignInReqDto);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> member_info = new HashMap<>();
        member_info.put("email", member.getEmail());
        member_info.put("token", jwtToken);
        return new ResponseEntity<>(new CommonResponse("member successfully logined", member_info), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/active/{email}")
    public ResponseEntity<CommonResponse> mailConfirm(@PathVariable(value = "email") String email) {
        emailService.sendEmail(email);

        return new ResponseEntity<>(new CommonResponse("Employee number transmitted successfully", email), HttpStatus.OK);
    }
}
