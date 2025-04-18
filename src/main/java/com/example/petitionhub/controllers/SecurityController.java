package com.example.petitionhub.controllers;

import com.example.petitionhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @GetMapping("/sign-in")
    public String signIn() {
        return "/auth/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "/auth/sign-up";
    }

    @PostMapping("/registration")
    public String saveNewUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        try {
            userService.save(username, password);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            return "redirect:/auth/sign-in";
        }
    }
}