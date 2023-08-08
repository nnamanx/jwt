package com.company.jwt.handler;

import com.company.jwt.exception.ApplicationException;
import com.company.jwt.model.dto.response.ExceptionResponse;
import com.company.jwt.model.enums.Exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionResponse> handle(ApplicationException applicationException) {
        Exceptions exceptions = applicationException.getExceptions();

        return ResponseEntity
                .status(exceptions.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .message(exceptions.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
