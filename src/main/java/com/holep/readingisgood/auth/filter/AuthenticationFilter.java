package com.holep.readingisgood.auth.filter;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.auth.data.LoginSucceedResponse;
import com.holep.readingisgood.auth.session.SessionHolder;
import com.holep.readingisgood.auth.util.AuthenticationResponseParser;
import com.holep.readingisgood.auth.util.SessionUtil;
import com.holep.readingisgood.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final SessionUtil sessionUtil;
    final SessionHolder sessionHolder;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                SessionUtil sessionUtil,
                                SessionHolder sessionHolder) {
        this.sessionUtil = sessionUtil;
        this.sessionHolder = sessionHolder;
        super.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // Initialize session here!

        String sessionId = sessionUtil.generateSessionId();
        sessionHolder.set(sessionId,
                (AuthUser) authResult.getPrincipal(),
                sessionUtil.getSessionTimeout());

        SecurityContextHolder.getContext().setAuthentication(authResult);

        AuthenticationResponseParser.prepareResponse(response,
                HttpStatus.OK.value(),
                LoginSucceedResponse.of(sessionId));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        AuthenticationResponseParser.prepareResponse(response,
                HttpStatus.UNAUTHORIZED.value(),
                ErrorResponse.of("401", failed.getMessage()));
    }


}
