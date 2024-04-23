package com.example.harbor_total.Annual.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualReceiveListResDto {
    private Long attendanceId;
    private String employeeId;
    private String statusCode;
    //Todo: 후결재 여부 체크 필요
    private String laterApproval;

    public static AnnualReceiveListResDto create(Long attendanceId, String employeeId, String statusCode){
        return AnnualReceiveListResDto.builder()
                .attendanceId(attendanceId)
                .employeeId(employeeId)
                .statusCode(statusCode)
                .build();
    }
}
