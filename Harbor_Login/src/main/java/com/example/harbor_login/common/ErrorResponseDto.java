package com.example.harbor_login.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponseDto {
    public static ResponseEntity<Map<String, Object>> makeMessage(HttpStatus status, String Message) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("status_message", status.getReasonPhrase());
        body.put("error_message", Message);

        return new ResponseEntity<>(body, status);
    }
}