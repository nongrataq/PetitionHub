package com.example.petitionhub.controllers;

import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/load-more")
@RequiredArgsConstructor
@Controller
public class LoaderController {
    private final PetitionService petitionService;
    private final PetitionEntityMapper petitionEntityMapper;


    //доделать кнопку loadMore
    @PostMapping
    public String loadMore(@RequestParam int page, Model model) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by(Sort.Direction.DESC, "date"));
        var petitionPage = petitionService.findAll(pageable);

        model.addAttribute("petitions", petitionEntityMapper.toPetitionDtos(petitionPage.getContent()));
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", petitionPage.hasNext());

        return "home/home";
    }
}