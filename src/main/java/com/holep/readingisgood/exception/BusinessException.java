package com.holep.readingisgood.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2233491462684998597L;

    public abstract String getCode();
    public abstract String getDescription();

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
