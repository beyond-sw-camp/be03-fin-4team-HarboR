package com.example.harbor_employee.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUpdateReq {
    private String scheduleId;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String scheduleTitle;
    private String scheduleComment;
    private String scheduleColor;
}
