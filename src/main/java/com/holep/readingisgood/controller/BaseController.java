package com.holep.readingisgood.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.validation.annotation.Validated;

@Validated
@SecurityRequirement(name = "Authorization")
public interface BaseController {
}
