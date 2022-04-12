package com.holep.readingisgood.auth.service;

import com.holep.readingisgood.auth.model.AuthUser;
import com.holep.readingisgood.auth.util.AuthType;

public interface AuthService {
    AuthType authType();
    AuthUser loadUserByUsername(String username);
}
