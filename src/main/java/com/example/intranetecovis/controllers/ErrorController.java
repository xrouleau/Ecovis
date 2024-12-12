package com.example.intranetecovis.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * Controlleur des erreurs
 */
@Controller
public class ErrorController {

    /***
     * Affiche la page d'erreur
     * @param request la requête
     * @param model le modèle
     * @return la page d'erreur
     */
    @GetMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("error", request.getAttribute("error"));
        return "error";
    }
}

