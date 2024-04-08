package com.example.harbor_employee.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaTestDto {
    private String employeeId;
    private String teamCode;
    private String positionCode;
    private String dutyCode;
    private String statusCode;
    private String genderCode;
    private String bankCode;
    private String socialSecurityNumber;
    private String address;
    private String careerYMD;
    private String joinDate;
    private String accountNumber;
    private String departmentCode;
    private String phone;
    private String email;
    private String name;
}
