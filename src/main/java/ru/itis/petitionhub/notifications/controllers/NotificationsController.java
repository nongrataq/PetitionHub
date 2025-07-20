package ru.itis.petitionhub.notifications.controllers;

import ru.itis.petitionhub.notifications.service.NotificationService;
import ru.itis.petitionhub.notifications.projections.NotificationProjection;
import ru.itis.petitionhub.notifications.model.NotificationEntity;
import ru.itis.petitionhub.security.details.UserEntityDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Controller
public class NotificationsController {
    private final NotificationService notificationService;

    @GetMapping
    public String notifications(Model model, @AuthenticationPrincipal UserEntityDetails user) {
        List<NotificationProjection> notificationEntityList = notificationService.findAllByReadAndRecipient(false, user.getUserEntity());
        model.addAttribute("notifications", notificationEntityList);
        return "user/notifications";
    }

    @PostMapping("/{id}")
    public String readNotification(@PathVariable(name = "id") UUID id) {
        Optional<NotificationEntity> notification = notificationService.findNotificationById(id);

        if (notification.isPresent()) {
            NotificationEntity notificationEntity = notification.get();
            notificationEntity.setRead(true);
            notificationService.save(notificationEntity);
        }

        return "redirect:/archive";
    }
}
