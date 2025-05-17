package com.example.petitionhub.controllers;

import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final PetitionService petitionService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("petitions", petitionService.findAll());
        return "home/home";
    }
}