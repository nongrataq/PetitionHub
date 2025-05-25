package com.example.petitionhub.controllers;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/search")
@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final PetitionEntityMapper petitionEntityMapper;

    @GetMapping
    public String search(
            @RequestParam(value = "query", required = false) String query,
            Model model,
            @PageableDefault(direction = Sort.Direction.DESC, size = 6, sort = "countOfSignatures") Pageable pageable
    ) {

        if (query != null && !query.trim().isEmpty()) {
            Page<PetitionEntity> petitions = searchService.searchPetitionByTitle(query.trim(), pageable);
            model.addAttribute("petitions", petitionEntityMapper.toPetitionDtos(petitions.getContent()));
            model.addAttribute("query", query);
        }

        return "petitions/search";
    }
}
