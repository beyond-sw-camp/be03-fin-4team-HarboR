package com.example.harbor_employee.schedule.dto;

import com.example.harbor_employee.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListRes {
    private Long scheduleId;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String scheduleTitle;
    private String scheduleComment;
    private String scheduleColor;

    public static ScheduleListRes mapToSchedule(Schedule schedule){
        return ScheduleListRes.builder()
                .scheduleId(schedule.getScheduleId())
                .scheduleEndDate(schedule.getScheduleEndDate())
                .scheduleStartDate(schedule.getScheduleStartDate())
                .scheduleEndTime(schedule.getScheduleEndTime())
                .scheduleStartTime(schedule.getScheduleStartTime())
                .scheduleTitle(schedule.getScheduleTitle())
                .scheduleComment(schedule.getScheduleComment())
                .scheduleColor(schedule.getScheduleColor())
                .build();

    }
}
