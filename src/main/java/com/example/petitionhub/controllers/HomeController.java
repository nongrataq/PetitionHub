package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.projections.PetitionProjection;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final PetitionService petitionService;

    @GetMapping
    public String home(Model model,@PageableDefault(sort = "countOfSignatures", direction = Sort.Direction.DESC, size = 6) Pageable pageable) {
        Page<PetitionProjection> petitionPage = petitionService.findAllProjections(pageable);
        model.addAttribute("petitions", petitionPage.getContent());

        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("hasNext", petitionPage.hasNext());
        model.addAttribute("hasPrevious", petitionPage.hasPrevious());
        return "home/home";
    }

    @GetMapping("/load-more")
    public String loadMore(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("countOfSignatures").descending());
        Page<PetitionProjection> petitionPage = petitionService.findAllProjections(pageable);

        model.addAttribute("petitions", petitionPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", petitionPage.hasNext());
        model.addAttribute("hasPrevious", petitionPage.hasPrevious());

        return "home/home";
    }
}