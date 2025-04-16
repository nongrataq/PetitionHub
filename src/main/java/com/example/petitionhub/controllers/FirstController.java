package com.example.petitionhub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class FirstController {

    @GetMapping
    public String main_page() {
        return "main-page";
    }

    @GetMapping("auth/sign-in")
    public String log_in_page() { return "sign-in-page"; }
}
