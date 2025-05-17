package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/specific-petition/{id}")
@RequiredArgsConstructor
@Controller
public class SpecificPetitionController {
    private final PetitionService petitionService;
    private final SignService signService;

    @GetMapping
    public String showSpecificPetition(
            Model model,
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal UserEntityDetails userDetails
    ) {
        PetitionEntity currentPetition = petitionService.findPetitionById(id);

        model.addAttribute("current_petition", currentPetition);

        boolean hasSigned = false;

        if (userDetails != null) {
            hasSigned = signService.hasUserSignedPetition(userDetails.getUserEntity(), currentPetition);
        }
        model.addAttribute("hasSigned", hasSigned);
        return "petitions/specific-petition";
    }
}