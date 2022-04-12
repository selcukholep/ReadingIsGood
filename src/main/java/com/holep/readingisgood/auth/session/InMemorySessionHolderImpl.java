package com.holep.readingisgood.auth.session;

import com.holep.readingisgood.auth.model.AuthUser;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemorySessionHolderImpl implements SessionHolder {

    private static final Map<String, AuthUser> SESSION_AUTH_MAP;
    private static final Map<String, Long> SESSION_EXP_MAP;

    static {
        SESSION_AUTH_MAP = new HashMap<>();
        SESSION_EXP_MAP = new HashMap<>();
    }

    @Override
    public AuthUser get(String sessionId) {

        if (isSessionExpired(sessionId)) {
            // TODO: Specify error such as SessionExpired.
            SESSION_AUTH_MAP.remove(sessionId);
            return null;
        }

        return SESSION_AUTH_MAP.get(sessionId);
    }

    /**
     *
     * @param sessionId unique SessionId key.
     * @param authUser User auth information.
     * @param expiration Session expiration in milliseconds.
     */
    @Override
    public void set(String sessionId, AuthUser authUser, long expiration) {
        SESSION_AUTH_MAP.put(sessionId, authUser);
        SESSION_EXP_MAP.put(sessionId, Instant.now().toEpochMilli() + expiration);
    }

    private boolean isSessionExpired(String sessionId) {
        if (SESSION_EXP_MAP.get(sessionId) == null) {
            return false;
        }

        return Instant.now().toEpochMilli() > SESSION_EXP_MAP.get(sessionId);
    }
}
