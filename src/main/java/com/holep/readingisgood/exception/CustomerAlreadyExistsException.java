package com.holep.readingisgood.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CustomerAlreadyExistsException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 972780754326610913L;

    private static final String CODE = "1005";
    private static final String DESCRIPTION = "Customer already exists.";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}

