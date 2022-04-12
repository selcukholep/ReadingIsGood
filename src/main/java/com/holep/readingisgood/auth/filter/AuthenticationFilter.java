package com.holep.readingisgood.auth.filter;

import com.holep.readingisgood.auth.session.SessionHolder;
import com.holep.readingisgood.auth.util.SessionUtil;
import com.holep.readingisgood.auth.model.AuthUser;
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
import java.nio.charset.StandardCharsets;

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
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
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

        prepareResponse(response, sessionId);
    }

    private void prepareResponse(HttpServletResponse response, String sessionId) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getOutputStream().write(
                """
                            {"token": "%s"}
                        """.formatted(sessionId)
                        .getBytes(StandardCharsets.UTF_8));
    }
}
