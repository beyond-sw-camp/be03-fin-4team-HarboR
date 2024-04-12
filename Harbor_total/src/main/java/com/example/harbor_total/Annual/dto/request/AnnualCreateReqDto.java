package com.example.harbor_total.Annual.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnualCreateReqDto {
    private Long attendenceId;
    private Double annualCount;
    private LocalDateTime adjustmentStartDate;
    private LocalDateTime adjustmentEndDate;
    private String adjustmentComment;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;

    public static AnnualCreateReqDto create(Long attendenceId, Double annualCount, LocalDateTime adjustmentStartDate, LocalDateTime adjustmentEndDate, String adjustmentComment, String firstSignId, String secondSignId, String thirdSignId){
        return AnnualCreateReqDto.builder()
                .attendenceId(attendenceId)
                .annualCount(annualCount)
                .adjustmentStartDate(adjustmentStartDate)
                .adjustmentEndDate(adjustmentEndDate)
                .adjustmentComment(adjustmentComment)
                .firstSignId(firstSignId)
                .secondSignId(secondSignId)
                .thirdSignId(thirdSignId)
                .build();
    }
}
