package com.example.harbor_total.commute.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommuteReqDTO {
        private Time attendanceTime;
        private Time leaveworkTime;
        private String status_code;
        private String attendanceDate;
        private String employeeId;

}