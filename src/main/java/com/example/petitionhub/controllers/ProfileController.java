package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;


@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{name}")
    public String profile(Model model,
                          @PathVariable(name = "name", required = false) String username
    ) {

        model.addAttribute("petitions", profileService.findPetitionsByAuthor_Username(username));
        model.addAttribute("authorName", username);
        model.addAttribute("count", profileService.findPetitionsByAuthor_Username(username).size());
        model.addAttribute("countOfSub", profileService.findPetitionsByAuthor_Username(username).stream()
                .map(PetitionDto::getNumberOfSignatures)
                .reduce(Integer::sum)
                .orElse(0));

        if (model.containsAttribute("petitionDto")) {
            model.addAttribute("petitionDto", model.getAttribute("petitionDto"));
        }

        return "user/profile";
    }

    @GetMapping
    public String myProfile(Principal principal) {
        return "redirect:/profile/" + principal.getName();
    }


}
