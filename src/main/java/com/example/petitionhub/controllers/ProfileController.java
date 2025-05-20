package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.ProfileService;
import com.example.petitionhub.services.impl.PetitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{name}")
    public String profile(Model model, @PathVariable(name = "name", required = false) String name) {

        model.addAttribute("petitions", profileService.findPetitionsByAuthor_Username(name));
        model.addAttribute("authorName", name);
        model.addAttribute("count" , profileService.findPetitionsByAuthor_Username(name).size());
        model.addAttribute("countOfSub", profileService.findPetitionsByAuthor_Username(name).stream()
                .map(PetitionDto::getNumberOfSignatures)
                .reduce(Integer::sum)
                .orElse(0));

        if (model.containsAttribute("petitionDto")) {
            model.addAttribute("petitionDto", model.getAttribute("petitionDto"));
        }

        return "user/profile";
    }

    @GetMapping
    public String redirectToProfile(Principal principal) {
        return "redirect:/profile/" + principal.getName();
    }




}
