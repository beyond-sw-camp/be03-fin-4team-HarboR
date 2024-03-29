package com.example.harbor_employee.client.dto;

import com.example.harbor_employee.global.support.Role;
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


}
