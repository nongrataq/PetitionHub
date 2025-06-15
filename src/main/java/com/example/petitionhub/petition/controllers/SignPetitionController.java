package com.example.petitionhub.petition.controllers;

import com.example.petitionhub.exceptions.AlreadySignedException;
import com.example.petitionhub.petition.services.SignService;
import com.example.petitionhub.petition.services.PetitionService;
import com.example.petitionhub.security.details.UserEntityDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@RequestMapping("/sign/{id}")
@RequiredArgsConstructor
@Controller
public class SignPetitionController {
    private final PetitionService petitionService;
    private final SignService signService;


    @PostMapping
    public String signPetition(
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal UserEntityDetails userDetails,
            RedirectAttributes redirectAttributes
    ) {
        try {
            signService.signPetition(petitionService.findPetitionById(id), userDetails.getUserEntity());
        } catch (AlreadySignedException e) {
            redirectAttributes.addFlashAttribute("signError", e.getMessage());
        }
        return "redirect:/specific-petition/" + id;
    }
}
