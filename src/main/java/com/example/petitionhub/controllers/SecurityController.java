package com.example.petitionhub.controllers;

import com.example.petitionhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String saveNewUser(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        try {
            userService.save(username, password);
            return "redirect:/auth/sign-in";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/auth/sign-up";
        }
    }
}