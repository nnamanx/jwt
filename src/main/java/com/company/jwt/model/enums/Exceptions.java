package com.company.jwt.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
    TOKEN_IS_INVALID_EXCEPTION(HttpStatus.BAD_REQUEST, "Token is invalid!");

    private final HttpStatus httpStatus;
    private final String message;
}
