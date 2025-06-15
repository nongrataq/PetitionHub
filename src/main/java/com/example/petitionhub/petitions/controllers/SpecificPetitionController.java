package com.example.petitionhub.petitions.controllers;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.petitions.services.PetitionService;
import com.example.petitionhub.petitions.services.SignService;
import com.example.petitionhub.security.details.UserEntityDetails;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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
        model.addAttribute("hasSigned", signService.hasUserSignedPetition(userDetails.getUserEntity(), currentPetition));
        model.addAttribute("images", currentPetition.getImages());

        return "petitions/specific-petition";
    }


}
