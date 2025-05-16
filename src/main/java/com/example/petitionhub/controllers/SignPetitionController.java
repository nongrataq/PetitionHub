package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
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
    public String signPetition(@PathVariable("id") UUID id) {

        PetitionEntity petition = petitionService.findPetitionById(id);
        petitionService.signPetition(petition);

        return "redirect:/specific-petition/" + id;
    }
}
