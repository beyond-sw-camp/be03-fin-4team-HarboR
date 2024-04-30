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
    private Long annualId;
    private String payStatusCode;
    private String firstApprovalId;
    private String secondApprovalId;
    private String thirdApprovalId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;
    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String adjustmentComment;
    public static AnnualListResDto create(Long annualId,String payStatusCode, String firstApprovalId,
                                          String firstApprovalDate, String secondApprovalId,
                                          String secondApprovalDate, String thirdApprovalId,
                                          String thirdApprovalDate, LocalDateTime workStartTime,
                                          LocalDateTime workEndTime, String adjustmentComment
                                          ) {
        return AnnualListResDto.builder()
                .annualId(annualId)
                .payStatusCode(payStatusCode)
                .firstApprovalId(firstApprovalId)
                .firstApprovalDate(firstApprovalDate)
                .secondApprovalId(secondApprovalId)
                .secondApprovalDate(secondApprovalDate)
                .thirdApprovalId(thirdApprovalId)
                .thirdApprovalDate(thirdApprovalDate)
                .workStartTime(workStartTime)
                .workEndTime(workEndTime)
                .adjustmentComment(adjustmentComment)
                .build();
    }
}
