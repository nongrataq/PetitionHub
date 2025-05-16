package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/sign/{id}")
@RequiredArgsConstructor
@Controller
public class SignPetitionController {
    private final PetitionService petitionService;

    @PostMapping()
    public String signPetition(@PathVariable("id") UUID id,
                               @AuthenticationPrincipal UserEntityDetails userDetails) {

        PetitionEntity petition = petitionService.findPetitionById(id);
        UserEntity user = userDetails.getUserEntity();
        petitionService.signPetition(petition, user);
        return "redirect:/specific-petition/" + id;
    }
}
