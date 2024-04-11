package com.example.harbor_total.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {


    @Builder
    public static class Index{

        private Time attendanceTime;
        private Time leaveworkTime;
        private String status_code;
        private String attendanceDate;
        private String employeeId;

    }
}
