package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MainPageController {
    private final UserService userService;
    private final PetitionService petitionService;

    @GetMapping("/")
    public String main_page() {
        return "main/main_page";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        UserEntity author = (UserEntity) userService.loadUserByUsername(principal.getName());
        if (!author.getPetitions().isEmpty()) {
            model.addAttribute("petitions", petitionService.findAllByAuthor(author));
        }
        return "main/profile";
    }

    @GetMapping("/search")
    public String search() {
        return "main/search";
    }
}