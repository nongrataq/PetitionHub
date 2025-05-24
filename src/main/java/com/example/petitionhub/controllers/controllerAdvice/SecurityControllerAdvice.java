package com.example.petitionhub.controllers.controllerAdvice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Objects;

@ControllerAdvice
public class SecurityControllerAdvice {
    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
    @ModelAttribute("principal")
    public String principalName(Principal principal) {
        return Objects.nonNull(principal) ? principal.getName() : null;
    }
}