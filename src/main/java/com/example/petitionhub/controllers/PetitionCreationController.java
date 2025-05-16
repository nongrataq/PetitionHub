package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.services.PetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/create-petition")
@RequiredArgsConstructor
public class PetitionCreationController {
    private final PetitionService petitionService;

    @PostMapping
    public String createPetition(@Valid PetitionDto petitionDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "petitions/create-petition";
        }

        redirectAttributes.addFlashAttribute("petitionDto", petitionService.createPetition(petitionDto));
        return "redirect:/profile";
    }

    @GetMapping
    public String pageOfCreatingPetitions() {
        return "petitions/create-petition";
    }
}
