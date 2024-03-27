package com.example.harbor_login.Login.service;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {
    private final LoginRepository loginRepository;
    private final JavaMailSender javaMailSender;  // 의존성 주입을 통해 필요한 객체를 가져옴
    private static String number;  // 랜덤 인증 코드
    private static final String SenderName = "windy7271@knou.ac.kr";


    public void sendEmail(String email) {
        MimeMessage message = createMail(email);
        javaMailSender.send(message);
    }
    private String CreateEmployeeId() {
        // 올해 년도
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String maxEmployeeNumber = loginRepository.findMaxEmployeeNumber();
        System.out.println(maxEmployeeNumber);

        // 만약 최대 사원번호가 없으면 HB + 올해 년도 + 0001 로 사원번호
        int nextNumber = 1;
        if (maxEmployeeNumber != null && maxEmployeeNumber.length() > 6) { //  HB20240001 형식이면
            try {
                nextNumber = Integer.parseInt(maxEmployeeNumber.substring(6)) + 1; // 마지막 4자리 숫자를 파싱하여 1 더하기.
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        number = "HB" + year + String.format("%04d", nextNumber);

        // 생성된 사원번호를 출력
        System.out.println("새로 발급된 사원번호: " + number);
        return number;
    }

    // 메일 양식 작성
    public MimeMessage createMail(String mail){
        Login member = loginRepository.findByEmail(mail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));
        if (member.getDelYn()) {
            throw new IllegalArgumentException("이미 사원번호가 발급 완료된 이메일입니다.");
        }
        if (!member.getDelYn()) {
            System.out.println("member update" + member);
            member.UpdateDelYn();
        }

        String EmployeeId = CreateEmployeeId();// 사원 번호 생성
        member.UpdateEmployeeId(EmployeeId); // 사원 번호 업데이트
//       사원번호 이메일로 보내주기
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(SenderName);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[WalkingMate] 회원가입을 위한 이메일 인증");
            String body = "";
            body += "<h1>" + "안녕하세요." + "</h1>";
            body += "<h1>" + "사원번호 발급하여 드립니다.." + "</h1>";
            body += "<h3>" + "회원 가입시 아래 사원번호와 같이 로그인 해주세요 ." + "</h3><br>";

            body += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
            body += "<h1 style='color:blue'>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
