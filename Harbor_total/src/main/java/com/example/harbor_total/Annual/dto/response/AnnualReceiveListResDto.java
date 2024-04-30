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
public class AnnualReceiveListResDto {
    private Long annualId;
    private String reqEmployeeId;
    private String payStatusCode;
    //Todo: 후결재 여부 체크 필요
    private String laterApproval;
    private String firstApprovalId;
    private String secondApprovalId;
    private String thirdApprovalId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;
    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String adjustmentComment;

    public static AnnualReceiveListResDto create(Long annualId, String reqEmployeeId,
                                                 String payStatusCode , String firstApprovalId,String firstApprovalDate,
                                                 String secondApprovalId,String secondApprovalDate,
                                                 String thirdApprovalId, String thirdApprovalDate,
                                                 LocalDateTime workStartTime, LocalDateTime workEndTime,
                                                 String adjustmentComment
                                                 ){
        return AnnualReceiveListResDto.builder()
                .annualId(annualId)
                .reqEmployeeId(reqEmployeeId)
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
