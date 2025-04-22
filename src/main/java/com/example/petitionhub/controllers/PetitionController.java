package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity.Petition;
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
        return "redirect:/petitions/all-petitions";
    }

    @GetMapping("/all-petitions")
    public String getPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "petitions/allPetitions";
    }

    @GetMapping("/{id}")
    public Petition getPetitionById(@PathVariable long id) {
        return petitionService.getPetitionById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePetitionById(@PathVariable long id) {
        petitionService.deletePetition(id);
    }

}
