package com.holep.readingisgood.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class AdminNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 972780754326610913L;

    private static final String CODE = "1000";
    private static final String DESCRIPTION = "Admin cannot be founded.";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}