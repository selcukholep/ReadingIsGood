package com.holep.readingisgood.auth.session;

import com.holep.readingisgood.auth.data.AuthUser;

public interface SessionHolder {
    AuthUser get(String sessionId);
    void set(String sessionId, AuthUser authUser, long expiration);
}
