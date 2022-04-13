package com.holep.readingisgood.auth;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // ATTENTION: Do nothing for this mapping.
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam @Parameter(schema = @Schema(type = "string", allowableValues = {"ADMIN", "CUSTOMER"})) String type,
            @RequestParam String username,
            @RequestParam String password
    ) {
        return ResponseEntity.ok("OK");
    }
}
