package com.example.harbor_employee.Employee.dto.response;

import com.example.harbor_employee.Employee.domain.EmployeeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployResponse {
    private List<Result> results = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private EmployeeCode positionCode;
    }
}

