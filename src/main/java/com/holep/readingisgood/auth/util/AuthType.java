package com.holep.readingisgood.auth.util;

import com.holep.readingisgood.exception.AuthTypeNotSupportedException;

import java.util.Arrays;

public enum AuthType {
    CUSTOMER,
    ADMIN;

    public static AuthType from(String type) {
        return
                Arrays.stream(AuthType.values())
                        .filter(authType -> authType.name().equals(type))
                        .findFirst()
                        .orElseThrow(AuthTypeNotSupportedException::new);
    }
}
