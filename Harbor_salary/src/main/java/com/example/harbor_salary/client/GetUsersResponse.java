package com.example.harbor_salary.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {
    private List<Result> results;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private String name;
        private String birth;
    }
}
