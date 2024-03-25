package com.example.harbor_login.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class CommonResponse<T> implements Serializable {
    private final String message;
    private final T result;
    private final HttpStatus status;

    /**
     * 생성자에서 필수적으로 넣어야하는 처리를 해야해서 커스텀
     */
    public static class ResponseBuilder<T> {

        private final String message;
        private T result;
        private final HttpStatus status;

        private ResponseBuilder(String message, HttpStatus httpStatus) {
            this.message = message;
            this.status = httpStatus;
        }

        public ResponseBuilder<T> result(T value) {
            result = value;
            return this;
        }

        public CommonResponse<T> build() {
            return new CommonResponse<>(this);
        }
    }

    public static <T> ResponseBuilder<T> builder(String message, HttpStatus httpStatus) {
        return new ResponseBuilder<>(message , httpStatus);
    }

    private CommonResponse(ResponseBuilder<T> responseBuilder) {
        message = responseBuilder.message;
        status = responseBuilder.status;
        result = responseBuilder.result;
    }
}
