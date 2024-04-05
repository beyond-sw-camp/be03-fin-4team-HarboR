package com.example.harbor_salary.dto.response;

import com.example.harbor_salary.domain.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeveranceDetailRes {

    private String employId;
    private String name;
    private String birth;
    private LocalDate MonthOfYear;
    private int severancePay;



    public static SeveranceDetailRes makeDetailRes(SeveranceDetailRes severanceDetailRes) {


        return SeveranceDetailRes.builder()
                .employId(severanceDetailRes.getEmployId())
                .name(severanceDetailRes.getName())
                .birth(severanceDetailRes.getBirth())
                .MonthOfYear(severanceDetailRes.getMonthOfYear())
                .build();

    }
        public void setSeverancePay(int severancePay){
            this.severancePay = severancePay;
        }
    }

