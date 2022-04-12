package com.holep.readingisgood.auth.service;

import com.holep.readingisgood.auth.service.AuthService;
import com.holep.readingisgood.auth.util.AuthConstants;
import com.holep.readingisgood.auth.util.AuthType;
import com.holep.readingisgood.exception.AuthTypeNotSupportedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AuthServiceFactory implements UserDetailsService {

    final List<AuthService> authServiceList;

    public AuthServiceFactory(List<AuthService> authServiceList) {
        this.authServiceList = authServiceList;
    }

    private AuthService getAuthServiceByType(AuthType authType) throws AuthTypeNotSupportedException {
        return authServiceList
                .stream()
                .filter(authService -> authService.authType().equals(authType))
                .findFirst()
                .orElseThrow(AuthTypeNotSupportedException::new);
    }

    private AuthType extractAuthTypeFromRequest() {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String typeString = request.getParameter(AuthConstants.PARAMETER_AUTH_TYPE);

        return AuthType.from(typeString);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthType authType = extractAuthTypeFromRequest();

        return getAuthServiceByType(authType)
                .loadUserByUsername(username);
    }
}
