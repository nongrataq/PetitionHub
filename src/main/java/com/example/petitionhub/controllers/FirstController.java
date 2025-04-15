package com.example.petitionhub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class FirstController {

    @GetMapping
    public String index() {
        return "main_page";
    }
}
