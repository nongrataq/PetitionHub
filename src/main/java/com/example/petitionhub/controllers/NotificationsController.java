package com.example.petitionhub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notifications")
@Controller
public class NotificationsController {
    @GetMapping
    public String notifications() {
        return "user/notifications";
    }
}
