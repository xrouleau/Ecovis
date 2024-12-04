package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.services.NouvelleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {
    @GetMapping("/connexion")
    public String connexion() {
        return "connexion-form";
    }

    @GetMapping("/enregistrer")
    public String enregistrer(Model model) {
        DTOInscription dto = new DTOInscription();
        model.addAttribute("dto", dto);
        return "enregistrement";
    }

    @PostMapping("/enregistrer")
    public String enregistrer(@Valid @ModelAttribute("dto") DTOInscription dto, @ModelAttribute("role") int role, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "enregistrement";
        }
        System.out.println(dto.getUsername());
        System.out.println(dto.getPrenom());
        System.out.println(dto.getNom());
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword1());
        System.out.println(dto.getPassword2());
        System.out.println(role);
        return "redirect:/nouvelles";
    }
}