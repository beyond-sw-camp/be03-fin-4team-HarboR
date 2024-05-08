package com.example.harbor_employee.schedule.dto;

import com.example.harbor_employee.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTeamListRes {
    private Long scheduleId;
    private String scheduleName;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String scheduleTitle;
    private String scheduleComment;
    private String scheduleColor;

    public static ScheduleTeamListRes mapToSchedule(Schedule schedule, String name){
        return ScheduleTeamListRes.builder()
                .scheduleId(schedule.getScheduleId())
                .scheduleName(name)
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
