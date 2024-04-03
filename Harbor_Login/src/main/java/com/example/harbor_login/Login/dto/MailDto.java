package com.example.harbor_login.Login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FindMailDto {
    private String email;
    private String title;
    private String contents;
}
