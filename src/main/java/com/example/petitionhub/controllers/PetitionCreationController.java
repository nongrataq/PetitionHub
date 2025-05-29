package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.PetitionCreationResultDto;
import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.TagService;
import com.example.petitionhub.services.impl.TagServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/create-petition")
@RequiredArgsConstructor
public class PetitionCreationController {
    private final PetitionService petitionService;

    @PostMapping
    public String createPetition(
            @RequestParam(required = false, name = "tagList") String tagList,
            @Valid PetitionDto petitionDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (Objects.nonNull(tagList) && !tagList.trim().isEmpty()) {
            petitionDto.setTagName(tagList.trim());
            model.addAttribute("selectedTag", "is-valid");
            petitionService.createPetitionWithTimeCheck(petitionDto);
            return "redirect:/profile";
        } else {
            model.addAttribute("selectedTag", "is-invalid");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("petitionDto", petitionDto);
            return "petitions/create-petition";
        }

        PetitionCreationResultDto result = petitionService.createPetitionWithTimeCheck(petitionDto);
        if (result.getCooldownLeft() > 0) {
            model.addAttribute("wait", true);
            model.addAttribute("cooldownLeft", result.getCooldownLeft());
            return "petitions/create-petition";
        }

        return "redirect:/profile";
    }

    @GetMapping
    public String pageOfCreatingPetitions() {
        return "petitions/create-petition";
    }
}