package com.example.harbor_employee.Eworks.dto.response;

import com.example.harbor_employee.Eworks.service.EworksService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EworksListResDto {
    private String payStatusCode;
    private String firstApprovalId;
    private String secondApprovalId;
    private String thirdApprovalId;
    private String firstApprovalDate;
    private String secondApprovalDate;
    private String thirdApprovalDate;

    public static EworksListResDto create(String payStatusCode, String firstApprovalId, String firstApprovalDate, String secondApprovalId, String secondApprovalDate, String thirdApprovalId, String thirdApprovalDate) {
        return EworksListResDto.builder()
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
