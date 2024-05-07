package com.example.harbor_login.Login.controller;


import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.request.*;
import com.example.harbor_login.Login.dto.response.GetUsersResponse;
import com.example.harbor_login.Login.service.EmailService;
import com.example.harbor_login.Login.service.LoginService;
import com.example.harbor_login.global.common.CommonResponse;
import com.example.harbor_login.global.config.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private JavaMailSender javaMailSender;


    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody LoginSignUpReqDto loginSignUpReqDto, BindingResult bindingResult) throws BindException {
        log.info("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        loginService.signup(loginSignUpReqDto);

        return new ResponseEntity<>(new CommonResponse("member successfully created", loginSignUpReqDto.getEmail()), HttpStatus.CREATED);
    }

        @PostMapping("/EmailLogin")
    public ResponseEntity<CommonResponse> Emailsignin(
            @Valid @RequestBody EmailLoginReqDto emailLoginReqDto,
            BindingResult bindingResult
    ) throws BindException {
            log.error("emailLoginReqDto.getEmail() = " + emailLoginReqDto.getEmail());
            log.error("emailLoginReqDto.getPassword() = " + emailLoginReqDto.getPassword());
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Login member = loginService.emailsignin(emailLoginReqDto);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString(), member.getEmployeeId());
        Map<String, Object> member_info = new HashMap<>();
        member_info.put("email", member.getEmail());
        member_info.put("token", jwtToken);
        member_info.put("role", member.getRole().name());
        member_info.put("empyloeeId", member.getEmployeeId());

        return new ResponseEntity<>(new CommonResponse("member successfully logined", member_info), HttpStatus.OK);
    }

    @PostMapping("/EmployeeIdLogin")
    public ResponseEntity<CommonResponse> EmployeeIdLogin(
            @Valid @RequestBody EmployeeLoginReqDto employeeLoginReqDto,
            BindingResult bindingResult

    ) throws BindException {
        log.error("emailLoginReqDto.getEmail() = " + employeeLoginReqDto.getEmployeeId());
        log.error("emailLoginReqDto.getPassword() = " + employeeLoginReqDto.getPassword());
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Login member = loginService.EmployeeIdsignin(employeeLoginReqDto);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString(), member.getEmployeeId());
        Map<String, Object> member_info = new HashMap<>();
        member_info.put("email", member.getEmail());
        member_info.put("token", jwtToken);
        member_info.put("role", member.getRole().name());
        member_info.put("empyloeeId", member.getEmployeeId());
//        System.out.println("userDetails = " + userDetails.getEmployeeId());

        return new ResponseEntity<>(new CommonResponse("member successfully logined", member_info), HttpStatus.OK);
    }

//    @GetMapping("/myinfo")
//    public ResponseEntity<CommonResponse> findMyInfo() {
//        return new ResponseEntity<>(new CommonResponse("member successfully logined", loginService.findMyInfo()), HttpStatus.OK);
//
//    }

    @PostMapping("/findId")
    public ResponseEntity<CommonResponse> findId(@RequestBody FindIdReqDto findIdReqDto) {
        Map<String, String> employeeId = loginService.findEmployeeId(findIdReqDto);
        return new ResponseEntity<>(new CommonResponse("find Id successfully", employeeId.get("EmployeeId")), HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<CommonResponse> pwFind(@RequestBody FindPasswordReq findPasswordReq) {
        loginService.userCheck(findPasswordReq);
        return new ResponseEntity<>(new CommonResponse("사용자가 확인 되었습니다.", ""), HttpStatus.OK);
    }

    @GetMapping("/TempPassword/{email}")
    public ResponseEntity<CommonResponse> mailConfirm1(@PathVariable(value = "email") String email) {
        emailService.sendEmailPw(email);
        return new ResponseEntity<>(new CommonResponse("tempPassword transmitted successfully", ""), HttpStatus.OK);
    }

    @PatchMapping("/changePw")
    public ResponseEntity<CommonResponse> pwCharge(@RequestBody ChargeRequestDto chargeRequestDto) {
        System.out.println("chargeRequestDto = " + chargeRequestDto);
        loginService.PwChange(chargeRequestDto);
        return new ResponseEntity<>(new CommonResponse("Password successfully change", ""), HttpStatus.OK);
    }

    @GetMapping("/detail")
    public GetUsersResponse getUsers(@RequestHeader("employeeId") String employeeId) {
        return loginService.getUserInfo(employeeId);
    }

    @GetMapping("/reissue/{employeeId}")
    public ResponseEntity<CommonResponse> reissueToken(@PathVariable("employeeId") String employeeId){
        System.out.println(employeeId);
        Login member = loginService.getMemberByEmployeeId(employeeId);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString(), member.getEmployeeId());

        Map<String, Object> member_info = new HashMap<>();
        member_info.put("email", member.getEmail());
        member_info.put("token", jwtToken);
        member_info.put("role", member.getRole().name());
        member_info.put("empyloeeId", member.getEmployeeId());

        return new ResponseEntity<>(new CommonResponse("token reissue successful", member_info), HttpStatus.OK);
    }
}