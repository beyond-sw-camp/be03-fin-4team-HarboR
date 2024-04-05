package com.example.harbor_login.Login.service;
import javax.mail.internet.MimeMessage;
import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.Login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmailService {
    private final LoginRepository loginRepository;
    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "아이디@gmail.com";
    private final PasswordEncoder passwordEncoder;

    private static String number;  // 랜덤 인증 코드
    private static final String SenderName = "windy7271@knou.ac.kr";


    public void sendEmail(String email) {
        MimeMessage message = createMail(email);
        javaMailSender.send(message);
    }
    public void sendEmailPw(String email){
        System.out.println();
        MimeMessage message = createPwMail(email);
        javaMailSender.send(message);
    }
    private String CreateEmployeeId() {
        // 올해 년도        member.updatePassword(passwordEncoder.encode(tempPassword);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String maxEmployeeNumber = loginRepository.findMaxEmployeeNumber();
        log.info("신규 입사자 Employee id : " + maxEmployeeNumber);

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



    public MimeMessage createPwMail(String mail){
        Login member = loginRepository.findByEmail(mail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 이메일입니다."));
        System.out.println("1");
        // 임시 비밀번호 생성
        String tempPassword = getTempPassword();
        MimeMessage message = javaMailSender.createMimeMessage();
        System.out.println("2");
        try {
            message.setFrom(SenderName);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[WalkingMate] 임시 비밀번호 발급");

            String body = "";
            body += "<h1>" + "안녕하세요." + "</h1>";
            body += "<h1>" + "임시비밀번호를 발급합니다." + "</h1>";
            body += "<h3>" + "새로운 비밀번호로 변경 바랍니다." + "</h3><br>";

            body += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
            body += "<h1 style='color:blue'>" + tempPassword + "</h1>"; // 임시 비밀번호를 이메일 본문에 추가
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");

            tempPassword = passwordEncoder.encode(tempPassword);
            member.updatePassword(tempPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String str = "";

        int idx;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        return str;
    }

//    public MailDto createMailAndChargePassword(FindPasswordReq findPasswordReq) {
//        String str = getTempPassword();
//
//        System.out.println("str = " + str);
//
//        MailDto dto = new MailDto();
//        dto.setTitle(findPasswordReq.getName()+"님의 임시비밀번호 안내 이메일 입니다.");
//        dto.setMessage("안녕하세요. 임시비밀번호 안내 관련 메일 입니다." + "[" + findPasswordReq.getName() + "]" + "님의 임시 비밀번호는 "
//                + str + " 입니다.");
//        updatePassword(str,findPasswordReq);
//        return dto;
//    }

//    public void updatePassword(String str, FindPasswordReq findPasswordReq) {
//        String pw = passwordEncoder.encode(str);
//        Login login = loginRepository.findByEmployeeId(findPasswordReq.getEmployeeId()).orElseThrow(()-> new IllegalArgumentException("없는 아이디"));
//        loginRepository.updatePw(pw, findPasswordReq.getEmployeeId());
//    }

//    public String getTempPassword() {
//        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
//        String str = "";
//
//        int idx = 0;
//        for (int i=0; i<10; i++) {
//            idx = (int) (charSet.length * Math.random());
//            str += charSet[idx];
//        }
//        return str;
//    }
//
//    public void mailSend(MailDto mailDto) {
//        System.out.println("이메일 전송 완료");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mailDto.getAddress());
//        message.setFrom(FROM_ADDRESS);
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getMessage());
//
//        javaMailSender.send(message);
//    }
//
//    public void userChange(ChargeRequestDto requestDto) {
//        Login user = loginRepository.findByEmployeeId(requestDto.getEmployeeId()).get();
//        if (user==null && !user.getPassword().equals(requestDto.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        } else {
//            String pw = passwordEncoder.encode(requestDto.getChangePw());
//            loginRepository.updatePw(pw, user.getEmployeeId());
//        }
//    }
}
