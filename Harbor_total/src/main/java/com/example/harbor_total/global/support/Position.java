package com.example.harbor_total.global.support;

import lombok.Getter;

@Getter
public enum Position {
    S10("S20"),
    S20("S30"),
    S30("S40"),
    S40("S50"),
    S50("S60"),
    S60("S60");

    private final String highPosition;
    Position(String highPosition){
        this.highPosition = highPosition;
    }
    public static Position getPositionByCode(String code) {
        if (code.startsWith("S1")) {
            return S10;
        } else if (code.startsWith("S2")) {
            return S20;
        } else if (code.startsWith("S3")) {
            return S30;
        } else if (code.startsWith("S4")) {
            return S40;
        } else if (code.startsWith("S5")) {
            return S50;
        } else if (code.startsWith("S6")) {
            return S60;
        } else {
            throw new IllegalArgumentException("해당하지 않는 직위 코드입니다.");
        }
    }
}
