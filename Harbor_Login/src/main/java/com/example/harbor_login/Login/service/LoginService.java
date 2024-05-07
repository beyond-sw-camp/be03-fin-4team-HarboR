package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.request.*;
import com.example.harbor_login.Login.dto.response.GetUsersResponse;
import com.example.harbor_login.Login.dto.response.LoginMemberResDto;
import com.example.harbor_login.Login.repository.LoginRepository;
import com.example.harbor_login.client.LoginToEmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginToEmployeeClient loginToEmployeeClient;
    private final EmailService emailService;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "본인의 이메일 주소를 입력하세요!";

    public void signup(LoginSignUpReqDto loginCreateReqDto) {
        Optional<Login> findMember = loginRepository.findByEmail(loginCreateReqDto.getEmail());
        if (findMember.isPresent()) {
            throw new EntityNotFoundException("이미 존재합니다.");
        }
        Login login = Login.createLogin(
                loginCreateReqDto.getEmail(),
                passwordEncoder.encode(loginCreateReqDto.getPassword()),
                loginCreateReqDto.getName(),
                loginCreateReqDto.getBirth()
        );

        loginRepository.save(login);

    }

    public Login emailsignin(EmailLoginReqDto emailLoginReqDto) {
        // 이메일을 기준으로 회원을 찾음
        Login member = loginRepository.findByEmail(emailLoginReqDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(emailLoginReqDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    public Login EmployeeIdsignin(EmployeeLoginReqDto employeeLoginReqDto) {
        Login member = loginRepository.findByEmployeeId(employeeLoginReqDto.getEmployeeId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));
        if (!passwordEncoder.matches(employeeLoginReqDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

//        // 사원 번호를 기준으로 회원을 찾음
//        Login employee = loginRepository.findByEmployeeId(loginSignInReqDto.getEmployeeId())
//                .orElse(null);
//
//        // 사원 번호가 존재하고, 회원과 일치하지 않는 경우
//        if (employee != null && !member.getEmail().equals(employee.getEmail())) {
//            throw new IllegalArgumentException("사원 번호가 올바르지 않습니다.");
//        }
//        // 회원 혹은 사원 번호가 존재하지 않는 경우
//        if (employee == null) {
//            throw new IllegalArgumentException("존재하지 않는 사원 번호입니다.");
//        }
        // 다 통과

    public Page<LoginMemberResDto> findAll(Pageable pageable) {

        return loginRepository.findAllByDelYnNotOrderByCreatedAt(true, pageable).map(LoginMemberResDto::mapToMemberResDto);
    }

    public String delete(String email) {
        Login login = loginRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("없는 회원입니다."));
        loginRepository.delete(login);
        return login.getEmployeeId();
    }

    public LoginMemberResDto findMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Login login = loginRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return LoginMemberResDto.mapToMemberResDto(login);
    }

    public GetUsersResponse getUserInfo(String id) {
        try{
            Login login = loginRepository.findByEmployeeId(id).orElseThrow(IllegalArgumentException::new);
            GetUsersResponse getUsersResponse = new GetUsersResponse();
            getUsersResponse.getResults().add(new GetUsersResponse.Result(login.getName(), login.getBirth()));
            System.out.println(getUsersResponse.getResults().get(0).getName());
            return getUsersResponse;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void findByEmail(String email) {
        Login member = loginRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));
        LoginMemberResDto loginMemberResDto = LoginMemberResDto.mapToMemberResDto(member);
        log.info("member basic dto 생성 전 호출");
        HttpStatus employee = loginToEmployeeClient.createEmployee(loginMemberResDto);

        log.info("Member basic Dto  생성" + employee);
    }

    public Map<String, String> findEmployeeId(FindIdReqDto findIdReqDto) {

        Login login = loginRepository.findByEmail(findIdReqDto.getEmail()).orElseThrow(() -> new IllegalArgumentException(" 존재하지 않은 이메일입니다"));


        Login user = loginRepository.findByEmailAndName(login.getEmail(), findIdReqDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("이메일과 이름이 일치하지 않습니다."));

        Map<String, String> result = new HashMap<>();
        result.put("EmployeeId", user.getEmployeeId());
        return result;
    }


    public void userCheck(FindPasswordReq findPasswordReq) {
        Optional<Login> userOptional = loginRepository.findByEmployeeId(findPasswordReq.getEmployeeId());

        // 사용자가 존재하지 않는 경우 예외 처리
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Login user = userOptional.get();

        // 사용자 이름이 요청된 이름과 일치하지 않는 경우 예외 처리
        if (!user.getName().equals(findPasswordReq.getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        System.out.println("성공");
        // 위의 조건들을 모두 통과했다면, 이메일 전송
        emailService.sendEmailPw("이메일이 전송되었습니다.");
    }

    public void PwChange(ChargeRequestDto chargeRequestDto) {
        Login user = loginRepository.findByEmployeeId(chargeRequestDto.getEmployeeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(chargeRequestDto.getTempPassword()));
        // 기존 비밀번호가 임시 비밀번호와 동일한지 확인
        if (passwordEncoder.matches(chargeRequestDto.getTempPassword(), user.getPassword())) {
            // 임시 비밀번호와 변경할 비밀번호가 동일하면 업데이트 수행
            String newPw = passwordEncoder.encode(chargeRequestDto.getChangePw());
            user.updatePassword(newPw);
        } else {
            // 임시 비밀번호와 다른 경우에는 예외를 던져서 처리
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Temporary password does not match");
        }
    }

    public Page<LoginMemberResDto> getcountallemployee(Pageable pageable) {

        return loginRepository.findAllByDelYnNotOrderByCreatedAt(false, pageable).map(LoginMemberResDto::mapToMemberResDto);
    }

    public Login getMemberByEmployeeId(String employeeId) {
        return loginRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
    }
}


