package com.example.harbor_total.Annual.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualListResDto {
    private String payStatusCode;
    private String firstApprovalId;
    private String secondApprovalId;
    private String thirdApprovalId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;

    public static AnnualListResDto create(String payStatusCode, String firstApprovalId, String firstApprovalDate, String secondApprovalId, String secondApprovalDate, String thirdApprovalId, String thirdApprovalDate) {
        return AnnualListResDto.builder()
                .payStatusCode(payStatusCode)
                .firstApprovalId(firstApprovalId)
                .firstApprovalDate(firstApprovalDate)
                .secondApprovalId(secondApprovalId)
                .secondApprovalDate(secondApprovalDate)
                .thirdApprovalId(thirdApprovalId)
                .thirdApprovalDate(thirdApprovalDate)
                .build();
    }
}
