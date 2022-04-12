package com.holep.readingisgood.auth.filter;

import com.holep.readingisgood.auth.session.SessionHolder;
import com.holep.readingisgood.auth.model.AuthUser;
import com.holep.readingisgood.auth.util.AuthenticationResponseParser;
import com.holep.readingisgood.domian.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    final SessionHolder sessionHolder;

    public AuthorizationFilter(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String sessionId = exportSessionId(request);

        if (sessionId == null) {
            AuthenticationResponseParser.prepareResponse(response,
                    HttpStatus.UNAUTHORIZED.value(), ErrorResponse.of("403", "Authorization header is invalid."));
            return;
        }

        AuthUser authUser = sessionHolder.get(sessionId);

        if (authUser == null) {
            AuthenticationResponseParser.prepareResponse(response,
                    HttpStatus.UNAUTHORIZED.value(), ErrorResponse.of("403", "Invalid session."));
            return;
        }

        setAuthentication(authUser);

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(AuthUser authUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authUser, null, authUser.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String exportSessionId(HttpServletRequest request) {

        String authenticationHeader = request.getHeader("Authorization");

        if (!StringUtils.hasText(authenticationHeader) || !authenticationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authenticationHeader.substring(7);
    }
}
