package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.mappers.UserEntityMapper;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final PetitionService petitionService;

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserEntityDetails userDetails) {
        UserEntity author = userDetails.getUserEntity();
        model.addAttribute("petitions", petitionService.findAllByAuthor(author));

        if (model.containsAttribute("petitionDto")) {
            model.addAttribute("petitionDto", model.getAttribute("petitionDto"));
        }

        return "profile";
    }
}
