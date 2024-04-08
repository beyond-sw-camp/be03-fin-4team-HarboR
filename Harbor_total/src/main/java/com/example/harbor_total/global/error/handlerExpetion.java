package com.example.harbor_total.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class handlerExpetion {

    @ExceptionHandler({BindException.class})
    @Order(value = Ordered.HIGHEST_PRECEDENCE) // 최우선 빈 등록, 에러터지면 일빠로 옴
    public ResponseEntity<?> handlerException(BindException ex) {
        return ErrorResponseDto.makeMessage(HttpStatus.CONFLICT,ex.getMessage());
    }
}
