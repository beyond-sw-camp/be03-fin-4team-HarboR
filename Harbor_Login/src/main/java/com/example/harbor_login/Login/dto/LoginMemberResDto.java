package com.example.harbor_login.Login.dto;

import com.example.harbor_login.Login.domain.Login;
import com.example.harbor_login.global.support.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberResDto {
    private String email;
    private String name;
    private String birth;
    private LocalDate createdAt;
    private Boolean delYn;
    private Role role;
    private String EmployeeId;

    public static LoginMemberResDto mapToMemberResDto(Login member) {
        return LoginMemberResDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .birth(member.getBirth())
                .createdAt(LocalDate.from(member.getCreatedAt()))
                .delYn(member.getDelYn())
                .role(Role.USER)
                .EmployeeId(member.getEmployeeId())
                .build();
    }
}
