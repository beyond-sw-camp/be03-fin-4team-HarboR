package com.example.harbor_employee.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameBirthDto {
    private String name;
    private String birth;
    private String leavingDate;
}
