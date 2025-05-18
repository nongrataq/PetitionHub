package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/search")
@Controller
@RequiredArgsConstructor
public class SearchController {

    private final PetitionService petitionService;

    @GetMapping
    public String search(@RequestParam(value = "query", required = false) String query, Model model) {
        List<PetitionEntity> petitions = new ArrayList<>();

        if (query != null && !query.trim().isEmpty()) {
            petitions = petitionService.searchPetitionByTitle(query.trim());
        }

        model.addAttribute("petitions", petitions);
        model.addAttribute("query", query != null ? query : "");
        return "petitions/search";
    }


}
