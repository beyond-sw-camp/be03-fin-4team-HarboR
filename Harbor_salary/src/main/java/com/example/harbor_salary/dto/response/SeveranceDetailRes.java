package com.example.harbor_salary.dto.response;

import com.example.harbor_salary.domain.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeveranceDetailRes {

    private String employId;
    private String name;
    private String birth;
    private String leavingDate;
    private int severancePay;



    public static SeveranceDetailRes makeDetailRes(String employId, String name , String birth, String leavingDate, int severancePay) {
        return SeveranceDetailRes.builder()
                .employId(employId)
                .name(name)
                .birth(birth)
                .leavingDate(leavingDate)
                .severancePay(severancePay)
                .build();

    }
        public void setSeverancePay(int severancePay){
            this.severancePay = severancePay;
        }



    }


