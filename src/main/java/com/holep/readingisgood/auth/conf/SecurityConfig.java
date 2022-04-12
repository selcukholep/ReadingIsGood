package com.holep.readingisgood.auth.conf;

import com.holep.readingisgood.auth.service.AuthServiceFactory;
import com.holep.readingisgood.auth.session.SessionHolder;
import com.holep.readingisgood.auth.util.SessionUtil;
import com.holep.readingisgood.auth.filter.AuthenticationFilter;
import com.holep.readingisgood.auth.filter.AuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final SessionUtil sessionUtil;
    final SessionHolder sessionHolder;
    final AuthServiceFactory authServiceFactory;
    final AuthorizationFilter authorizationFilter;
    final PasswordEncoder passwordEncoder;

    public SecurityConfig(SessionUtil sessionUtil,
                          SessionHolder sessionHolder, AuthServiceFactory authServiceFactory,
                          AuthorizationFilter authorizationFilter, PasswordEncoder passwordEncoder) {
        this.sessionUtil = sessionUtil;
        this.sessionHolder = sessionHolder;
        this.authServiceFactory = authServiceFactory;
        this.authorizationFilter = authorizationFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/auth/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), sessionUtil, sessionHolder))
                .addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .logout().invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authServiceFactory)
                .passwordEncoder(passwordEncoder);
    }
}
