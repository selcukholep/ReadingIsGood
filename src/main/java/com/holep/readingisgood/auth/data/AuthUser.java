package com.holep.readingisgood.auth.data;

import com.holep.readingisgood.auth.util.AuthType;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AuthUser extends EnabledUserDetails {

    @Serial
    private static final long serialVersionUID = 4109178056288531391L;

    private UUID id;
    private String username;
    private String password;
    private AuthType authType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(AuthRole.from(authType));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
