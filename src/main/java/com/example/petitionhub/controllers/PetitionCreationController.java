package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionCreationResultDto;
import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.services.PetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/create-petition")
@RequiredArgsConstructor
public class PetitionCreationController {
    private final PetitionService petitionService;

    @PostMapping
    public String createPetition(@Valid PetitionDto petitionDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("petitionDto", petitionDto);
            return "petitions/create-petition";
        }

        PetitionCreationResultDto result = petitionService.createPetitionWithTimeCheck(petitionDto);

        if (result.getCooldownLeft() > 0) {
            model.addAttribute("wait", true);
            model.addAttribute("cooldownLeft", result.getCooldownLeft());
            return "petitions/create-petition";
        }

        return "redirect:/profile";
    }

    @GetMapping
    public String pageOfCreatingPetitions() {
        return "petitions/create-petition";
    }
}
