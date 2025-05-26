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
            @RequestParam(required = false, name = "tagName") String tagName,
            @Valid PetitionDto petitionDto,
            BindingResult bindingResult,
            Model model
    ) {
        // Обработка тега
        if (tagName != null && !tagName.trim().isEmpty()) {
            // Если введен тег вручную - валидируем его
            petitionDto.setTagName(tagName.trim());
        } else if (tagList != null && !tagList.trim().isEmpty()) {
            // Если выбран тег из списка - просто устанавливаем без валидации
            petitionDto.setTagName(tagList.trim());
        } else {
            bindingResult.rejectValue("tagName", "error.tagName", "Выберите или введите тег");
        }


        if (bindingResult.hasErrors()) {
            List<FieldError> errorsToShow = bindingResult.getFieldErrors().stream()
                    .filter(error -> !(error.getField().equals("tagName") && tagList != null && !tagList.trim().isEmpty()))
                    .collect(Collectors.toList());

            model.addAttribute("errors", errorsToShow);
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