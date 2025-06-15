package com.example.petitionhub.user.notification.controllers;

import com.example.petitionhub.user.notification.service.NotificationService;
import com.example.petitionhub.security.details.UserEntityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/archive")
public class ArchiveController {
    private final NotificationService notificationService;

    @GetMapping
    public String archive(Model model, @AuthenticationPrincipal UserEntityDetails user) {
        model.addAttribute("readNotifications",
                notificationService.findAllByReadAndRecipient(true, user.getUserEntity()));

        return "user/archive";
    }
}
