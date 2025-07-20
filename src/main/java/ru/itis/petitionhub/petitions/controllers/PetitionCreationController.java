package ru.itis.petitionhub.petitions.controllers;

import ru.itis.petitionhub.petitions.dto.PetitionDto;
import ru.itis.petitionhub.petitions.services.PetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/create-petition")
@RequiredArgsConstructor
public class PetitionCreationController {
    private final PetitionService petitionService;

    @PostMapping
    public String createPetition(
            @Valid PetitionDto petitionDto,
            BindingResult bindingResult,
            @RequestParam(name = "images") List<MultipartFile> images,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("petitionDto", petitionDto);
            return "petitions/create-petition";
        }

        if (!petitionService.canCreatePetitionNow()) {
            long cooldownLeft = petitionService.getRemainingCooldown();
            model.addAttribute("wait", true);
            model.addAttribute("cooldownLeft", cooldownLeft);
            return "petitions/create-petition";
        }

        petitionService.savePetition(petitionDto, images);
        return "redirect:/profile";
    }


    @GetMapping
    public String pageOfCreatingPetitions() {
        return "petitions/create-petition";
    }
}
