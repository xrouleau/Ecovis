package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.services.NouvelleService;
import com.example.intranetecovis.services.RoleService;
import com.example.intranetecovis.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SecurityController {

    private RoleService roleService;
    private UtilisateurService utilisateurService;

    public SecurityController(RoleService roleService, UtilisateurService utilisateurService) {
        this.roleService = roleService;
        this.utilisateurService = utilisateurService;
    }

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
    public String enregistrer(@Valid @ModelAttribute("dto") DTOInscription dto,  BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "enregistrement";
        }
        if (Objects.equals(dto.getPassword1(), dto.getPassword2())) {
            utilisateurService.save(dto);
        } else {
            return "enregistrement";
        }
        return "redirect:/nouvelles";
    }
}