package com.example.harbor_login.Login.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {
    private List<Result> results = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private String name;
        private String birth;
    }
}
