package com.holep.readingisgood.auth.service;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.auth.util.AuthType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("auth-admin-service")
public class AdminAuthServiceImpl implements AuthService {

    private final static String DEFAULT_USERNAME = "admin";
    private final static String DEFAULT_PASSWORD = "admin";

    final PasswordEncoder passwordEncoder;

    public AdminAuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthType authType() {
        return AuthType.ADMIN;
    }

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!username.equals(DEFAULT_USERNAME)) {
            throw new UsernameNotFoundException("Bad credentials.");
        }

        return getDefaultAdminUser();
    }

    private AuthUser getDefaultAdminUser() {
        AuthUser authUser = new AuthUser();

        authUser.setId(UUID.randomUUID());
        authUser.setUsername(DEFAULT_USERNAME);
        authUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        authUser.setAuthType(AuthType.ADMIN);

        return authUser;
    }
}
