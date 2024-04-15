package com.example.harbor_salary.salary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    }


