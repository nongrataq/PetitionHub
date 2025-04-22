package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller("/")
public class MainPageController {
    private final UserService userService;

    @GetMapping
    public String main_page(Model model, Principal principal) {
        if (principal != null) {
            UserEntity user = userService.findUserEntityByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        return "main_page";
    }

    @GetMapping("profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("search")
    public String search(Model model) {
        return "search";
    }
}