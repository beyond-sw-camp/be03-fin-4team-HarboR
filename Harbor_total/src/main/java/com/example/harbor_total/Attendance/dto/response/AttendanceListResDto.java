package com.example.harbor_total.Attendance.dto.response;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.dto.request.AttendanceFlexibleWorkReqDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class AttendanceListResDto {
    private Long attendanceId;
    private LocalDateTime workStartTime;
    private LocalDateTime workEndTime;
    private String workPolicy;
    private String EmployeeId;
    private double annualRemain;

    public static AttendanceListResDto toDto(Attendance attendance) {
        return AttendanceListResDto.builder()
                .attendanceId(attendance.getAttendanceId())
                .workStartTime(attendance.getWorkStartTime())
                .workEndTime(attendance.getWorkEndTime())
                .workPolicy(attendance.getWorkPolicy())
                .EmployeeId(attendance.getEmployee().getEmployeeId())
                .annualRemain(attendance.getEmployee().getAnnualRemain())
                .build();
    }
}
