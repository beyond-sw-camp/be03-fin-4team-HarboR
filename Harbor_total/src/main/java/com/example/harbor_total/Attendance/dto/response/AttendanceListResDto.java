package com.example.harbor_total.Attendance.dto.response;

import com.example.harbor_total.Annual.domain.Annual;
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

    public static <T> AttendanceListResDto toDto(T requestwork, AttendanceFlexibleWorkReqDto attendanceFlexibleWorkReqDto, String employeeId) {
        if (requestwork instanceof Attendance) {
            Attendance attendance = (Attendance) requestwork;
            return AttendanceListResDto.builder()
                    .attendanceId(attendance.getAttendanceId())
                    .EmployeeId(employeeId)
                    .workStartTime(attendance.getWorkStartTime())
                    .workEndTime(attendance.getWorkEndTime())
                    .workPolicy(attendance.getWorkPolicy())
                    .annualRemain(attendance.getEmployee().getAnnualRemain())
                    .build();
        } else if (requestwork instanceof Annual) {

            Annual annual = (Annual) requestwork;
            System.out.println("annual.getAnnualCount() = " + annual.getAnnualCount());
            // 여기서는 attendanceFlexibleWorkReqDto가 아닌 annual 객체로부터 값을 가져와야 합니다.
            return AttendanceListResDto.builder()
                    .attendanceId(annual.getAnnualId())
                    .EmployeeId(employeeId)
                    .workStartTime(annual.getAdjustmentDate())
                    .workEndTime(annual.getAdjustmentEndDate())
                    .workPolicy(attendanceFlexibleWorkReqDto.getWorkPolicy())
                    .annualRemain(annual.getAnnualCount())
                    .firstSignId(annual.getFirstSignId())
                    .secondSignId(annual.getSecondSignId())
                    .thirdSignId(annual.getThirdSignId())
                    .build();
        } else {

            return null;
        }
    }
}
