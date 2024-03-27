package com.example.harbor_login.global.common;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.repository.LoginRepository;
import com.example.harbor_login.global.support.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {
    //CommandLineRunner를 통해 스프링빈으로 등록되는 시점에 run메서드 실행

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataLoader(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (loginRepository.findByEmail("admin@test.com").isEmpty()) {
            Login login = Login.builder()
                    .name("운영자")
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("harbor123!!"))
                    .employeeId("HB20240001")
                    .birth("960211")
                    .role(Role.ADMIN)
                    .delYn(true)
                    .build();
            loginRepository.save(login);
        }
    }
}
