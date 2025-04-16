package com.example.petitionhub.controllers;

import com.example.petitionhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }

    @PostMapping("/registration")
    public String saveNewUser(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "redirect:/";
    }

}