package com.example.harbor_employee.global.support;

import lombok.Getter;

/**
 * 상위 부서 테이블
 */
@Getter
public enum Department {
    E01("T01"),
    E02("T01"),
    E03("T01"),
    E04("T02"),
    E05("T02"),
    E06("T02"),
    E07("T03"),
    E08("T03"),
    E09("T03"),
    E10("T04"),
    E11("T04"),
    E12("T04"),
    E13("T05"),
    E14("T05"),
    E15("T05"),
    T01("K1000"),
    T02("K1000"),
    T03("K1000"),
    T04("K1000"),
    T05("K1000"),
    K1000("K1000");

    private final String code;
    private Department(String code){
        this.code = code;
    }
}
