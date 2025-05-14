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
    public String signUp(@Valid @ModelAttribute(name = "signUpDto") SignUpDto signUpDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "auth/sign-up";
        }
        try {
            signUpService.signUp(signUpDto);
            redirectAttributes.addFlashAttribute("message", "Sign up successful");
            return "redirect:/auth/sign-in";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("userAlreadyExist", e.getMessage());
            return "redirect:/auth/sign-up";
        }
    }
}