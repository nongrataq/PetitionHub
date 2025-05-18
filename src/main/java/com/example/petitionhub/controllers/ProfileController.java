package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.security.details.UserEntityDetails;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.impl.PetitionServiceImpl;
import lombok.RequiredArgsConstructor;
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
    private final SignatureRepository signatureRepository;

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserEntityDetails userDetails) {
        UserEntity author = userDetails.getUserEntity();
        model.addAttribute("petitions", petitionService.findAllByAuthor(author));
        model.addAttribute("authorName",userDetails.getUsername());
        model.addAttribute("count" , petitionService.findAllByAuthor(author).size());
        model.addAttribute("countOfSub", petitionService.findAllByAuthor(author).stream()
                .map(PetitionDto::getNumberOfSignatures)
                .reduce(Integer::sum)
                .orElse(0));

        if (model.containsAttribute("petitionDto")) {
            model.addAttribute("petitionDto", model.getAttribute("petitionDto"));
        }

        return "user/profile";

    }




}
