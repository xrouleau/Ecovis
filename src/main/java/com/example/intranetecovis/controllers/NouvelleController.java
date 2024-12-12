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

/***
 * Controlleur des nouvelles s'occupe de tout ce qui est en lien avec les nouvelles
 */
@EnableMethodSecurity
@Controller
public class NouvelleController {

    // déclaration des variables de classe
    private NouvelleService nouvelleService;
    private UtilisateurService utilisateurService;

    /***
     * Constructeur de la classe NouvelleController
     * @param nouvelleService Service des nouvelles
     * @param utilisateurService Service des utilisateurs
     */
    public NouvelleController(NouvelleService nouvelleService, UtilisateurService utilisateurService) {
        this.nouvelleService = nouvelleService;
        this.utilisateurService = utilisateurService;
    }

    /***
     * Affiche toutes les nouvelles de l'intranet publiées
     * @param model le modèle
     * @param session la session
     * @return la page contenant toutes les nouvelles de l'intranet publiées
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/nouvelles")
    public String nouvelles(Model model, HttpSession session) {
        List<Nouvelle> nouvelles = nouvelleService.getAll();
        model.addAttribute("nouvelles", nouvelles);
        return "liste-nouvelles";
    }

    /***
     * Affiche une nouvelle
     * @param id id de la nouvelle à afficher
     * @param model le modèle
     * @return la page de la nouvelle
     */
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

    /***
     * Affiche toutes les nouvelles de l'utilisateur connecté
     * @param model le modèle
     * @param session la session
     * @return la page contenant toutes les nouvelles de l'utilisateur connecté
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/nouvellesUtilisateur")
    public String nouvellesUtilisateur(Model model, HttpSession session) {
        List<Nouvelle> nouvelles = nouvelleService.findByUtilisateurContains((Utilisateur) session.getAttribute("user"));
        model.addAttribute("nouvelles", nouvelles);
        return "nouvelles-utilisateur";
    }

    /***
     * Met la nouvelle en brouillon
     * @param id id de la nouvelle à mettre en brouillon
     * @return la méthode nouvellesUtilisateur
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/retirerNouvelle/{id}")
    public String retirerNouvelle(@PathVariable int id) {
        nouvelleService.retirer(id);
        return "redirect:/nouvellesUtilisateur";
    }

    /***
     * Affiche la page qui permet de modifier une nouvelle
     * @param id id le la nouvelle à modifier
     * @param model le modèle
     * @return la page qui permet de modifier une nouvelle
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/modifierNouvelle/{id}")
    public String modifierNouvelle(@PathVariable int id, Model model) {
        model.addAttribute("nouvelle", nouvelleService.findById(id));
        return "modifier-nouvelle";
    }

    /***
     * Permet de publier une nouvelle
     * @param id id de la nouvelle à modifier
     * @param model le modèle
     * @return vers la méthode nouvellesUtilisateur
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/publierNouvelle/{id}")
    public String publierNouvelle(@PathVariable int id, Model model) {
        nouvelleService.publier(id);
        return "redirect:/nouvellesUtilisateur";
    }

    /***
     * Retire un utilisateur de la liste d'auteurs d'une nouvelle
     * @param idUtilisateur id de l'utilisateur à retirer
     * @param idNouvelle id de la nouvelle
     * @return vers la méthode modifierNouvelle pour la nouvelle modifié
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/retirerUtilisateurNouvelle/{idUtilisateur}/{idNouvelle}")
    public String retirerUtilisateurNouvelle(@PathVariable int idUtilisateur, @PathVariable int idNouvelle) {
        nouvelleService.retirerUtilisateur(idNouvelle, idUtilisateur);
        return "redirect:/modifierNouvelle/" + idNouvelle;
    }

    /***
     * Ajoute un utilisateur à la liste d'auteur d'une nouvelle
     * @param idNouvelle id de la nouvelle
     * @param request la requête
     * @return vers la méthode modifierNouvelle pour la nouvelle modifié
     */
    @PreAuthorize("hasRole('COMM')")
    @PostMapping("/ajouterUtilisateurNouvelle/{idNouvelle}")
    public String ajouterUtilisateurNouvelle(@PathVariable int idNouvelle, HttpServletRequest request) {
        String username = request.getParameter("username");
        nouvelleService.ajouterUtilisateur(idNouvelle, username);
        return "redirect:/modifierNouvelle/" + idNouvelle;
    }

    /**
     * Supprime une nouvelle
     * @param id id de la nouvelle à supprimer
     * @return vers la méthode nouvellesUtilisateur
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/supprimerNouvelle/{id}")
    public String retirerUtilisateurNouvelle(@PathVariable int id) {
        nouvelleService.deleteById(id);
        return "redirect:/nouvellesUtilisateur";
    }

    /***
     * Affiche la page permettant d'ajouter une nouvelle
     * @return la page permettant d'ajouter une nouvelle
     */
    @PreAuthorize("hasRole('COMM')")
    @GetMapping("/ajouterNouvelle")
    public String ajouterNouvelle() {
        return "ajouter-nouvelle";
    }

    /***
     * Crée une nouvelle
     * @param request la requête
     * @param session la session
     * @return vers la méthode nouvellesUtilisateur
     */
    @PreAuthorize("hasRole('COMM')")
    @PostMapping("/creerNouvelle")
    public String creerNouvelle(HttpServletRequest request, HttpSession session) {
        String titre = request.getParameter("titre");
        Utilisateur u = (Utilisateur) session.getAttribute("user");
        nouvelleService.save(titre, u);
        return "redirect:/nouvellesUtilisateur";
    }
}
