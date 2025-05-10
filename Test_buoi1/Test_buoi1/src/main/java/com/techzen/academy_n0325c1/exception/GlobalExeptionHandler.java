package com.techzen.academy_n0325c1.exception;

import com.techzen.academy_n0325c1.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // nơi để lắng nge lỗi trả về
public class GlobalExeptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException e) {
        Errorcode errorcode = e.getErrorcode();

        return ResponseEntity.status(errorcode.getStatus()).body(
                ApiResponse.builder()
                        .code(errorcode.getCode())
                        .message(e.getMessage())
                        .build()
        );
    }
}
