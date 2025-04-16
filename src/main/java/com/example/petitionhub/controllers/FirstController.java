package com.example.petitionhub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String main_page() {
        return "main_page";
    }

    @GetMapping("auth/sign-in")
    public String log_in_page() { return "sign-in-page"; }
}