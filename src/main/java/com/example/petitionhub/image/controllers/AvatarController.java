package com.example.petitionhub.image.controllers;

import com.example.petitionhub.image.services.ImageService;
import com.example.petitionhub.image.ImageTypes;
import com.example.petitionhub.security.details.UserEntityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;


@RequestMapping("/avatar")
@RequiredArgsConstructor
@Controller
public class AvatarController {
    private final ImageService imageService;

    @PostMapping
    public String uploadAvatar(@RequestParam(name = "file") MultipartFile file,
                               @AuthenticationPrincipal UserEntityDetails user,
                               RedirectAttributes redirectAttributes) {
        try {
            imageService.uploadAvatarFromUser(file, user.getUserEntity(), ImageTypes.AVATAR);
            redirectAttributes.addFlashAttribute("successAvatar", "Аватар успешно обновлен!");
            return "redirect:/profile";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorAvatar", "Не удалось загрузить аватарку.");
            return "redirect:/profile";
        }
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAvatar(@PathVariable(name = "id") UUID id) {
        return imageService.getAvatar(id);
    }

}
