package com.holep.readingisgood.auth.conf;

import com.holep.readingisgood.auth.conf.properties.SessionProperties;
import com.holep.readingisgood.auth.filter.AuthenticationFilter;
import com.holep.readingisgood.auth.filter.AuthorizationFilter;
import com.holep.readingisgood.auth.service.AuthServiceFactory;
import com.holep.readingisgood.auth.session.SessionHolder;
import com.holep.readingisgood.auth.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(SessionProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final SessionUtil sessionUtil;
    final SessionHolder sessionHolder;
    final AuthServiceFactory authServiceFactory;
    final AuthorizationFilter authorizationFilter;
    final PasswordEncoder passwordEncoder;
    final SessionProperties sessionProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(sessionProperties.getExcludedPaths().toArray(String[]::new));
    }
}
