package com.example.harbor_login.Login.controller;


import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.*;
import com.example.harbor_login.Login.service.EmailService;
import com.example.harbor_login.Login.service.LoginService;
import com.example.harbor_login.global.common.CommonResponse;
import com.example.harbor_login.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

        System.out.println("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        loginService.signup(loginSignUpReqDto);

        return new ResponseEntity<>(new CommonResponse("member successfully created", loginSignUpReqDto.getEmail()), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse> signin(
            @Valid @RequestBody LoginSignInReqDto loginSignInReqDto,
            BindingResult bindingResult
//            @AuthenticationPrincipal CustomUserDetails userDetails

    ) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Login member = loginService.signin(loginSignInReqDto);
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString(), member.getEmployeeId());
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

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<GetUsersResponse> getUserDetail(@PathVariable(name = "id") String id) {
        System.out.println(id);
        GetUsersResponse login = loginService.getUserInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(login);
    }

    @GetMapping("/myinfo")
    public ResponseEntity<CommonResponse> findMyInfo() {
        return new ResponseEntity<>(new CommonResponse("member successfully logined", loginService.findMyInfo()), HttpStatus.OK);

    }

    @GetMapping("/findId")
    public ResponseEntity<CommonResponse> findId(@RequestBody FindIdReqDto findIdReqDto) {
        Map<String, String> employeeId = loginService.findEmployeeId(findIdReqDto);
        return new ResponseEntity<>(new CommonResponse("find Id successfully", findIdReqDto), HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<CommonResponse> pwFind(@RequestBody FindPasswordReq findPasswordReq) {
        loginService.userCheck(findPasswordReq);
        return new ResponseEntity<>(new CommonResponse("사용자가 확인 되었습니다.", findPasswordReq), HttpStatus.OK);
    }

    @PostMapping("/TempPassword/{email}")
    public ResponseEntity<CommonResponse> mailConfirm1(@PathVariable(value = "email") String email) {
        emailService.sendEmailPw(email);


        return new ResponseEntity<>(new CommonResponse("tempPassword transmitted successfully", email), HttpStatus.OK);
    }

    @PostMapping("/changePw")
    public ResponseEntity<CommonResponse> pwCharge(@RequestBody ChargeRequestDto chargeRequestDto) {
        loginService.PwChange(chargeRequestDto);
        return new ResponseEntity<>(new CommonResponse("Password successfully change", chargeRequestDto), HttpStatus.OK);
    }
}



//    @GetMapping("/myinfo")
//    public ResponseEntity<CommonResponse> findMyInfo(@RequestHeader("myEmail") String email) {
//        return new ResponseEntity<>(new CommonResponse("member successfully logined", loginService.findMyInfo(email)), HttpStatus.OK);
//    }

//    //Email과 name의 일치여부를 check하는 컨트롤러
//    @GetMapping("/check/findPw")
//    public @ResponseBody Map<String, Boolean> pw_find(String email, String name) {
//        Map<String, Boolean> json = new HashMap<>();
//        boolean pwFindCheck = loginService.userEmailCheck(email, name);
//        System.out.println(pwFindCheck);
//        json.put("check", pwFindCheck);
//        return json;
//    }
//
//    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
//    @PostMapping("/check/findPw/sendEmail")
//    public @ResponseBody void sendEmail(String email, String name) {
//        MailDto dto = loginService.createMailAndChangePassword(email, name);
//        loginService.mailSend(dto);

//    }

