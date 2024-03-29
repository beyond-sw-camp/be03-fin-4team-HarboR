package com.example.harbor_employee.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final String employeeId;
    private final String role;
    @Builder.Default
    private final boolean accountNonExpired = true;
    @Builder.Default
    private final boolean accountNonLocked = true;
    @Builder.Default
    private final boolean credentialsNonExpired = true;
    @Builder.Default
    private final boolean enabled = true;
    @Builder.Default
    private final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
}
