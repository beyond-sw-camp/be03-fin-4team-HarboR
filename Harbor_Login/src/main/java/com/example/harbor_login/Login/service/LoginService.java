package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.dto.LoginSignInReqDto;
import com.example.harbor_login.Login.dto.LoginSignUpReqDto;
import com.example.harbor_login.Login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(LoginSignUpReqDto loginCreateReqDto) {
        Optional<Login> findMember = loginRepository.findByEmail(loginCreateReqDto.getEmail());
        if (findMember.isPresent()) {
            throw new EntityNotFoundException("이미 존재합니다.");
        }
        System.out.println("findMember = " + findMember);
        Login login = Login.createLogin(
                loginCreateReqDto.getEmail(),
                passwordEncoder.encode(loginCreateReqDto.getPassword()),
                loginCreateReqDto.getName(),
                loginCreateReqDto.getBirth()
        );

        loginRepository.save(login);
//      밑에 이메일 보내기 추가혜정

    }

    public Login signin(LoginSignInReqDto loginSignInReqDto) {
        Login member = loginRepository.findByEmail(loginSignInReqDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));
        if (!passwordEncoder.matches(loginSignInReqDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 사원번호 발급 후 사원번호 비교.
        return member;
    }
}
