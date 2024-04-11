package com.example.harbor_employee.Eworks.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalReqDto {
    private Long payId;
    private String employeeId;
    private Boolean approvalStatus;
    private Boolean force;
}
