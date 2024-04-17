package com.example.harbor_total.Attendance.dto.request;


import com.example.harbor_total.commute.domain.CommuteRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class AttendanceFlexibleWorkReqDto {

    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String adjustmentComment;
    private String workPolicy;
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;
}
