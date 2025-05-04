package com.example.petitionhub.controllers;

import com.example.petitionhub.dto.UserAuthDto;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.services.UserService;
import com.example.petitionhub.utils.MappingUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;
    private final MappingUtils mappingUtils;

    @GetMapping("/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping("/registration")
    public String saveNewUser(@Valid UserAuthDto userAuthDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/auth/sign-up";
        }

        try {
            userService.saveUserToDataBase(mappingUtils.mapUserAuthDtoToUserEntity(userAuthDto));
            return "redirect:/auth/sign-in";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/sign-up";
        }
    }
}