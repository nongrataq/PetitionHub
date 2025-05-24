package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.SignUpDto;
import com.example.petitionhub.services.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/auth/sign-up")
@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping
    public String signUp(@Valid SignUpDto signUpDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {

        model.addAttribute("isFormSubmitted", true);

        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("signUpDto", signUpDto);
            return "auth/sign-up";
        }

        try {
            signUpService.signUp(signUpDto);
            redirectAttributes.addFlashAttribute("message", "Вы успешно зарегистрировались!");
            return "redirect:/auth/sign-in";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("userAlreadyExist", e.getMessage());
            model.addAttribute("signUpDto", signUpDto);
            return "redirect:/auth/sign-up";
        }
    }
}