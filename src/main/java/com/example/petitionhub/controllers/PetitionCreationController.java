package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionCreationResultDto;
import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.TagService;
import com.example.petitionhub.services.impl.TagServiceImpl;
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
    private final TagService tagService;

    @PostMapping
    public String createPetition(@Valid PetitionDto petitionDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("petitionDto", petitionDto);
            model.addAttribute("tags", tagService.getAllTags());
            return "petitions/create-petition";
        }

        PetitionCreationResultDto result = petitionService.createPetitionWithTimeCheck(petitionDto);

        if (result.getCooldownLeft() > 0) {
            model.addAttribute("wait", true);
            model.addAttribute("cooldownLeft", result.getCooldownLeft());
            model.addAttribute("tags", tagService.getAllTags());
            return "petitions/create-petition";
        }

        return "redirect:/profile";
    }

    @GetMapping
    public String pageOfCreatingPetitions(Model model) {
        model.addAttribute("petitionDto", new PetitionDto());
        model.addAttribute("tags", tagService.getAllTags());

        return "petitions/create-petition";
    }
}
