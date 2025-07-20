package ru.itis.petitionhub.global;

import ru.itis.petitionhub.security.details.UserEntityDetails;
import ru.itis.petitionhub.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.Objects;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {
    private final NotificationService notificationService;

    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
    @ModelAttribute("principal")
    public String principalName(Principal principal) {
        return Objects.nonNull(principal) ? principal.getName() : null;
    }

    @ModelAttribute("hasNotification")
    public boolean hasNotification(@AuthenticationPrincipal UserEntityDetails user) {
        return Objects.nonNull(user) && !notificationService.findAllByReadAndRecipient(false, user.getUserEntity()).isEmpty();
    }
}