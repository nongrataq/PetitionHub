package com.example.petitionhub.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth/sign-in")
@Controller
public class SignInController {
    @GetMapping
    public String getSignInPage() {
        return "auth/sign-in";
    }
}
