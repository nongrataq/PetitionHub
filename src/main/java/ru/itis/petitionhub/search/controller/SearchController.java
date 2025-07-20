package ru.itis.petitionhub.search.controller;

import ru.itis.petitionhub.petitions.projections.PetitionProjection;
import ru.itis.petitionhub.search.service.SearchService;
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

    @GetMapping
    public String search(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "tag", required = false) String tag,
            Model model,
            @PageableDefault(direction = Sort.Direction.DESC, size = 6, sort = "countOfSignatures") Pageable pageable
    ) {

        if (query != null && !query.trim().isEmpty()) {
            Page<PetitionProjection> petitions = searchService.searchPetitionByTitle(query.trim(), pageable);
            model.addAttribute("petitions", petitions.getContent());
            model.addAttribute("query", query);
            model.addAttribute("searchType", "title");

        } else if (tag != null && !tag.trim().isEmpty()) {
            Page<PetitionProjection> petitions = searchService.searchPetitionByTag(tag.trim(), pageable);
            model.addAttribute("petitions", petitions.getContent());
            model.addAttribute("tag", tag);
            model.addAttribute("searchType", "tag");
        }

        return "petitions/search";
    }
}