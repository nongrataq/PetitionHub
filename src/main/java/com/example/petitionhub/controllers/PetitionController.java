package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.utils.MappingUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PetitionController {
    private final PetitionService petitionService;
    private final MappingUtils mappingUtils;

    @PostMapping("/create-petition")
    public String createPetition(@Valid PetitionDto petitionDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            return "redirect:/petitions/create-petition";
        }
        PetitionEntity savedPetition = petitionService.createPetition(
                mappingUtils.mapPetitionDtoToPetitionEntity(petitionDto)
        );

        return "redirect:/specific-petition/" + savedPetition.getId();
    }

    @GetMapping("/specific-petition/{id}")
    public String pageOfSpecificPetition(@PathVariable Long id, Model model) {
        model.addAttribute("p", petitionService.findById(id));
        return "/petitions/specific-petition";
    }


    @GetMapping("/petitions")
    public String pageOfCreatingPetitions() {
        return "/petitions/create-petitions";
    }

}
