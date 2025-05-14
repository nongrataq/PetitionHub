package com.example.petitionhub.controllers;

import com.example.petitionhub.security.details.UserEntityDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/sign-in")
@Controller
public class SignInController {
    @GetMapping
    public String getSignInPage(@AuthenticationPrincipal UserEntityDetails userDetails) {
        if (userDetails != null) {
            return "redirect:/";
        }
        return "auth/sign-in";
    }
}
