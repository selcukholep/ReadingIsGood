package com.holep.readingisgood.exception;

import java.io.Serial;

public class AuthTypeNotSupportedException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2845196564926647261L;

    private static final String CODE = "1003";
    private static final String DESCRIPTION = "Auth type not supported.";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
