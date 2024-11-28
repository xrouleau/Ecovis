package com.example.intranetecovis.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("error", request.getAttribute("error"));
        return "error";
    }
}

