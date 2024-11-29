package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.services.NouvelleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NouvelleController {
    private final NouvelleService nouvelleService;

    public NouvelleController(NouvelleService nouvelleService) {
        this.nouvelleService = nouvelleService;
    }

    @GetMapping("/nouvelles")
    public String nouvelles(Model model) {
        List<Nouvelle> nouvelles = nouvelleService.getAll();
        model.addAttribute("nouvelles", nouvelles);
        return "liste-nouvelles";
    }

}
