package com.example.intranetecovis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * Controlleur pour la page d'accueil
 */
@Controller
public class HomeController {

    /***
     * Affiche la page d'accueil
     * @return la page d'accueil
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}