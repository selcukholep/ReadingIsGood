package com.holep.readingisgood.auth.data;

import com.holep.readingisgood.auth.util.AuthType;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

public class AuthRole implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 8945357966693767797L;

    private final String authority;

    public AuthRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static AuthRole from(AuthType authType) {
        String role = "ROLE_" + authType.name();
        return new AuthRole(role);
    }
}
