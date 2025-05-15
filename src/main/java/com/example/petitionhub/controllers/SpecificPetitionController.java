package com.example.petitionhub.controllers;


import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping ("/specific-petition")
@RequiredArgsConstructor
@Controller
public class SpecificPetitionController {
    private final PetitionService petitionService;

    @GetMapping("/{id}")
    public String showSpecificPetition(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("current_petition", petitionService.findPetitionById(id));
        return "petitions/specific-petition";
    }

}
