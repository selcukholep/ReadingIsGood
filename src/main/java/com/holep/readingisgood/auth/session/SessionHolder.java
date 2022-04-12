package com.holep.readingisgood.auth.session;

import com.holep.readingisgood.auth.model.AuthUser;

public interface SessionHolder {
    AuthUser get(String sessionId);
    void set(String sessionId, AuthUser authUser, long expiration);
}
