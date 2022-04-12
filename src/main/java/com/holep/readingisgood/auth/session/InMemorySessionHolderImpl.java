package com.holep.readingisgood.auth.session;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.auth.util.AuthType;
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

        // Demo Admin User
        AuthUser admin = new AuthUser();
        admin.setAuthType(AuthType.ADMIN);
        admin.setUsername("selcukholep@gmail.com");
        SESSION_AUTH_MAP.put("8f4a0e56-012b-46c6-a29c-052425468f7c", admin);
        SESSION_EXP_MAP.put("8f4a0e56-012b-46c6-a29c-052425468f7c", Instant.now().toEpochMilli() + 10000000);
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
