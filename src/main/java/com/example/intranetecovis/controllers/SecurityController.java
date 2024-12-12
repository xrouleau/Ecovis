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

/***
 * Controlleur de sécurité s'occupe de tous ce qui est en lien avec le compte d'un utilisateur
 */
@Controller
public class SecurityController {

    // déclaration des variables de classe
    private RoleService roleService;
    private UtilisateurService utilisateurService;

    /***
     * Constructeur de la classe SecurityController
     * @param roleService service de la classe role
     * @param utilisateurService service de la classe utilisateur
     */
    public SecurityController(RoleService roleService, UtilisateurService utilisateurService) {
        this.roleService = roleService;
        this.utilisateurService = utilisateurService;
    }

    /***
     * Affiche le formulaire de connexion
     * @return le formulaire de connexion
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/connexion")
    public String connexion() {
        return "connexion-form";
    }

    /***
     * Affiche le compte de l'utilisateur connecté
     * @param session la session
     * @return le compte de l'utilisateur connecté
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/compte")
    public String compte(HttpSession session) {
        // accède à l'utilisateur connecté qui est storé dans la session
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        session.setAttribute("userAModifier", user.getId());
        return "compte";
    }

    /***
     * Modifie le mot de passe de l'utilisateur
     * @param request la requête
     * @param session la session
     * @return redirige vers la méthode compte
     */
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