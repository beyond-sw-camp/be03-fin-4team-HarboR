package com.example.harbor_total.commute.dto.res;

import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.commute.domain.CommuteRecord;
import com.example.harbor_total.global.support.Code;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.harbor_total.global.support.Code.*;

@Builder
@Data
@Slf4j
public class CommuteListResDto {
    private Date attendanceDate;
    private Time workStartTime;
    private Time workEndTime;
    private String workPolicy;
    //    지각
    private String tardy;

    public static List<CommuteListResDto> toDto(Attendance attendance, List<CommuteRecord> commuteRecords) {
        List<CommuteListResDto> result = new ArrayList<>(); // 결과를 저장할 리스트 생성

        if (O04.name().equals(attendance.getWorkPolicy()) ||
                O03.name().equals(attendance.getWorkPolicy()) ||
                O09.name().equals(attendance.getWorkPolicy())) {
            LocalDate startDate = attendance.getWorkStartTime().toLocalDate(); // 휴가 시작
            LocalDate endDate = attendance.getWorkEndTime().toLocalDate(); // 휴가 종료
            while (!startDate.isAfter(endDate)) {
                CommuteListResDto dto = CommuteListResDto.builder() // 빌더를 사용하여 객체 생성
                        .attendanceDate(Date.valueOf(startDate))
                        .workPolicy(attendance.getWorkPolicy())
                        .tardy(X02.name() + " 정상")
                        .build();

                result.add(dto);
                // 다음 날짜
                startDate = startDate.plusDays(1);
            }
//            O01("출근"),O02("퇴근"), O03("출장"), O04("휴가"), O05("휴계"), O06("고정"), O07("시차"), O08("반차"), O09("병가"),
        } else {
            Date day = Date.valueOf(attendance.getWorkStartTime().toLocalDate());
            CommuteRecord commuteRecord = commuteRecords.stream()
                    .filter(x -> x.getAttendanceDate().equals(day))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("매칭되는 날짜가 없습니다."));
            // 일반 출근
            CommuteListResDtoBuilder builder = CommuteListResDto.builder()
                    .attendanceDate(day)
                    .workPolicy(attendance.getWorkPolicy())
                    .workStartTime(commuteRecord.getAttendanceTime())
                    .workEndTime(commuteRecord.getLeaveworkTime())
                    .tardy(X02.name() + " 정상");
            if (O01.name().equals(attendance.getWorkPolicy()) ||
                    O07.name().equals(attendance.getWorkPolicy())
            ) {
                if (commuteRecord.getAttendanceTime().toLocalTime().isAfter(attendance.getWorkStartTime().toLocalTime())) {
                    builder.tardy(X01.name() + " 지각");
                }
            }
            CommuteListResDto dto = builder.build();

            result.add(dto);
        }

        return result;
    }
}
