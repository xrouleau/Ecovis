package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.services.NouvelleService;
import com.example.intranetecovis.services.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@EnableMethodSecurity
@Controller
public class NouvelleController {
    private NouvelleService nouvelleService;
    private UtilisateurService utilisateurService;

    public NouvelleController(NouvelleService nouvelleService, UtilisateurService utilisateurService) {
        this.nouvelleService = nouvelleService;
        this.utilisateurService = utilisateurService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/nouvelles")
    public String nouvelles(Model model, HttpSession session) {
        List<Nouvelle> nouvelles = nouvelleService.getAll();
        List<Nouvelle> nouvellesPub = new ArrayList<>();
        for (Nouvelle nouvelle : nouvelles) {
            if (nouvelle.getPublie()) {
                nouvellesPub.add(nouvelle);
            }
        }
        model.addAttribute("nouvelles", nouvellesPub);
        return "liste-nouvelles";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/lire/{id}")
    public String lire(@PathVariable int id, Model model) {
        Nouvelle nouvelle = nouvelleService.findById(id);
        if (nouvelle != null) {
            model.addAttribute("nouvelle", nouvelle);
            return "nouvelle";
        } else {
            return "redirect:/nouvelles";
        }
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/nouvellesUtilisateur")
    public String nouvellesUtilisateur(Model model, HttpSession session) {
        List<Nouvelle> nouvelles = nouvelleService.findByUtilisateurContains((Utilisateur) session.getAttribute("user"));
        model.addAttribute("nouvelles", nouvelles);
        return "nouvelles-utilisateur";
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/retirerNouvelle/{id}")
    public String retirerNouvelle(@PathVariable int id) {
        nouvelleService.retirer(id);
        return "redirect:/nouvellesUtilisateur";
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/modifierNouvelle/{id}")
    public String modifierNouvelle(@PathVariable int id, Model model) {
        model.addAttribute("nouvelle", nouvelleService.findById(id));
        return "modifier-nouvelle";
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/publierNouvelle/{id}")
    public String publierNouvelle(@PathVariable int id, Model model) {
        nouvelleService.publier(id);
        return "redirect:/nouvellesUtilisateur";
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/retirerUtilisateurNouvelle/{idUtilisateur}/{idNouvelle}")
    public String retirerUtilisateurNouvelle(@PathVariable int idUtilisateur, @PathVariable int idNouvelle) {
        System.out.println(idNouvelle);
        System.out.println(idUtilisateur);
        nouvelleService.retirerUtilisateur(idNouvelle, idUtilisateur);
        return "redirect:/modifierNouvelle/" + idNouvelle;
    }

    @PreAuthorize("hasRole('COMM')")
    @PostMapping("/ajouterUtilisateurNouvelle/{idNouvelle}")
    public String ajouterUtilisateurNouvelle(@PathVariable int idNouvelle, HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println(idNouvelle);
        System.out.println(username);
        nouvelleService.ajouterUtilisateur(idNouvelle, username);
        return "redirect:/modifierNouvelle/" + idNouvelle;
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/supprimerNouvelle/{id}")
    public String retirerUtilisateurNouvelle(@PathVariable int id) {
        nouvelleService.deleteById(id);
        return "redirect:/nouvellesUtilisateur";
    }

    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/ajouterNouvelle")
    public String ajouterNouvelle() {
        return "ajouter-nouvelle";
    }

    @PreAuthorize("hasRole('COMM')")
    @PostMapping("/creerNouvelle")
    public String creerNouvelle(HttpServletRequest request, HttpSession session) {
        String titre = request.getParameter("titre");
        Utilisateur u = (Utilisateur) session.getAttribute("user");
        nouvelleService.save(titre, u);
        return "redirect:/nouvellesUtilisateur";
    }
}
