package com.holep.readingisgood.auth.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

    final PasswordEncoder passwordEncoder;

    public PasswordGenerator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generate(String email) {
        return passwordEncoder.encode(email.substring(0, email.indexOf("@")));
    }
}
