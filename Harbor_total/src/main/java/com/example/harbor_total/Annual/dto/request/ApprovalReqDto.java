package com.example.harbor_total.Annual.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalReqDto {
    private Long annualId;
    private Boolean approvalStatus;
    private Boolean force; // => 이전 사람이 휴가중인지 체크하고(프론트?)
}