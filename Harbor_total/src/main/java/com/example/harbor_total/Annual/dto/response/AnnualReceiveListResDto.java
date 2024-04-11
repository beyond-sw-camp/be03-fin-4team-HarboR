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

    public static AnnualReceiveListResDto create(Long attendanceId, String employeeId, String statusCode){
        return AnnualReceiveListResDto.builder()
                .attendanceId(attendanceId)
                .employeeId(employeeId)
                .statusCode(statusCode)
                .build();
    }
}
