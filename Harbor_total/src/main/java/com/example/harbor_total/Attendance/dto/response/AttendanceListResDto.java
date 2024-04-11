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
    private String firstSignId;
    private String secondSignId;
    private String thirdSignId;

    public static AttendanceListResDto toDto(Attendance attendance, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto) {
        return AttendanceListResDto.builder()
                .attendanceId(attendance.getAttendanceId())
                .workStartTime(attendance.getWorkStartTime())
                .workEndTime(attendance.getWorkEndTime())
                .workPolicy(attendance.getWorkPolicy())
                .EmployeeId(attendance.getEmployee().getEmployeeId())
                .annualRemain(attendance.getEmployee().getAnnualRemain())
                .firstSignId(attendanceFlexibleWorkReqDto.getFirstSignId())
                .secondSignId(attendanceFlexibleWorkReqDto.getSecondSignId())
                .thirdSignId(attendanceFlexibleWorkReqDto.getThirdSignId())
                .build();
    }
}
