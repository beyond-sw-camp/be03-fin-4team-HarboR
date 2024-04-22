package com.example.harbor_total.commute.dto.req;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommuteListReqDto {
    private LocalDateTime month;
}
