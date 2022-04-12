package com.holep.readingisgood.auth.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class AuthTypeNotSupportedException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 2845196564926647261L;

    private static final String DESCRIPTION = "Auth type not supported.";

    public AuthTypeNotSupportedException() {
        super(DESCRIPTION);
    }
}
