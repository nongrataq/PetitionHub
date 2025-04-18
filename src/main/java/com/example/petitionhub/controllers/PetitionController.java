package com.example.petitionhub.controllers;

import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionController {
    private final PetitionService petitionService;

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        petitionService.createPetition(title, description);
        return "redirect:/";
    }

    @GetMapping("/create-petition")
    public String getPetitions(Model model) {
        return "/petitions/create-petitions";
    }

}