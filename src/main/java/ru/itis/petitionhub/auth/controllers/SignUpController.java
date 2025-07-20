package ru.itis.petitionhub.auth.controllers;

import ru.itis.petitionhub.auth.dto.SignUpDto;
import ru.itis.petitionhub.auth.services.SignUpService;
import ru.itis.petitionhub.exceptions.UserAccountAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public String signUp(
            @Valid SignUpDto signUpDto,
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
        } catch (UserAccountAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("userAlreadyExist", e.getMessage());
            model.addAttribute("signUpDto", signUpDto);
            return "redirect:/auth/sign-up";
        }
    }
}