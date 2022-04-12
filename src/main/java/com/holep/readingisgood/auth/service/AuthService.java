package com.holep.readingisgood.auth.service;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.auth.util.AuthType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {
    AuthType authType();
    AuthUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
