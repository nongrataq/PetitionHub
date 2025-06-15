package com.example.petitionhub.petition.controllers;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.petition.services.PetitionService;
import com.example.petitionhub.petition.services.SignService;
import com.example.petitionhub.security.details.UserEntityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/specific-petition")
@RequiredArgsConstructor
@Controller
public class SpecificPetitionController {
    private final PetitionService petitionService;
    private final SignService signService;
    private final PetitionEntityMapper petitionEntityMapper;

    @GetMapping("/{id}")
    public String showSpecificPetition(
            Model model,
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal UserEntityDetails userDetails
    ) {
        PetitionEntity currentPetition = petitionService.findPetitionById(id);

        model.addAttribute("current_petition", petitionEntityMapper.toPetitionDto(currentPetition));

        model.addAttribute("hasSigned",
                signService.hasUserSignedPetition(userDetails.getUserEntity(), currentPetition)
        );
        return "petitions/specific-petition";
    }
}