package com.holep.readingisgood.auth.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionUtil {

    private static final long SESSION_TIMEOUT = 2 * 60 * 60 * 1000;

    public long getSessionTimeout() {
        return SESSION_TIMEOUT;
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}
