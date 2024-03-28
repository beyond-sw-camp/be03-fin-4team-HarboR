package com.example.harbor_login.Login.controller;


import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.LoginMemberResponseDto;
import com.example.harbor_login.Login.dto.LoginSignInReqDto;
import com.example.harbor_login.Login.dto.LoginSignUpReqDto;
import com.example.harbor_login.Login.repository.LoginRepository;
import com.example.harbor_login.Login.service.EmailService;
import com.example.harbor_login.Login.service.LoginService;
import com.example.harbor_login.global.common.CommonResponse;
import com.example.harbor_login.global.config.CustomUserDetails;
import com.example.harbor_login.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    private final LoginRepository loginRepository;


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
    public ResponseEntity<CommonResponse> signin(
            @Valid @RequestBody LoginSignInReqDto loginSignInReqDto,
            BindingResult bindingResult
//            @AuthenticationPrincipal CustomUserDetails userDetails

    ) throws BindException {
        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Login member = loginService.signin(loginSignInReqDto);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString(),member.getEmployeeId());
        Map<String, Object> member_info = new HashMap<>();
        member_info.put("email", member.getEmail());
        member_info.put("token", jwtToken);
        member_info.put("role", member.getRole().name());
        member_info.put("empyloeeId", member.getEmployeeId());
//        System.out.println("userDetails = " + userDetails.getEmployeeId());

        return new ResponseEntity<>(new CommonResponse("member successfully logined", member_info), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/active/{email}")
    public ResponseEntity<CommonResponse> mailConfirm(@PathVariable(value = "email") String email) {
        emailService.sendEmail(email);

        return new ResponseEntity<>(new CommonResponse("Employee number transmitted successfully", email), HttpStatus.OK);
    }

    @GetMapping(value = "/login/{email}/detail")
    public ResponseEntity<CommonResponse> getUserDetail(@PathVariable(name = "email") String email){
        Login login = loginService.getUserInfo(email);
        return new ResponseEntity<>(new CommonResponse("member successfully logined", login), HttpStatus.OK);
    }

    @GetMapping("/myinfo")
    public ResponseEntity<CommonResponse> findMyInfo() {
        return new ResponseEntity<>(new CommonResponse("member successfully logined", loginService.findMyInfo()), HttpStatus.OK);

    }
//    @GetMapping("/myinfo")
//    public ResponseEntity<CommonResponse> findMyInfo(@RequestHeader("myEmail") String email) {
//        return new ResponseEntity<>(new CommonResponse("member successfully logined", loginService.findMyInfo(email)), HttpStatus.OK);
//    }
}
