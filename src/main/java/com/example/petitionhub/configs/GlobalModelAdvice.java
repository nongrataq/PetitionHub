package com.example.petitionhub.configs;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalModelAdvice {
    @ModelAttribute("user")
    public String addUserToModel(Principal principal) {
        return principal != null ? principal.getName() : null;
    }
}