package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.services.NouvelleService;
import com.example.intranetecovis.services.RoleService;
import com.example.intranetecovis.services.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAnonymous()")
    @GetMapping("/connexion")
    public String connexion() {
        return "connexion-form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/compte")
    public String compte(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        session.setAttribute("userAModifier", user.getId());
        return "compte";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modifierMDPCompte")
    public String modifierMDPCompte(HttpServletRequest request, HttpSession session) {
        String old = request.getParameter("oldPassword");
        String p1 = request.getParameter("password1");
        String p2 = request.getParameter("password2");
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        utilisateurService.modifyPasswordCompte(user.getId(), old, p1, p2);
        return "redirect:/compte";
    }
}