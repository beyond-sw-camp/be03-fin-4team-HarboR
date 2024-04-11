package com.example.harbor_total.Attendance.dto.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceFlexibleWorkReqDto {

    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String workPolicy;
}
