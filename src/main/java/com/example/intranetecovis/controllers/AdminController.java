package com.example.intranetecovis.controllers;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

/***
 * Controlleur de l'administrateur qui prend en charge toutes les actions que seul l'administrateur peut effectuer
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {

    // déclaration des variables de classe
    private RoleService roleService;
    private UtilisateurService utilisateurService;

    /***
     * Constructeur de la classe AdminController
     * @param roleService Service de Role
     * @param utilisateurService Service des Utilisateur
     */
    public AdminController(RoleService roleService, UtilisateurService utilisateurService) {
        this.roleService = roleService;
        this.utilisateurService = utilisateurService;
    }

    /***
     * Affiche la page permettant d'ajouter un utilisateur
     * @param model le modèle
     * @return la page permettant d'ajouter un utilisateur
     */
    @GetMapping("/enregistrer")
    public String enregistrer(Model model) {
        DTOInscription dto = new DTOInscription();
        model.addAttribute("dto", dto);
        return "enregistrement";
    }

    /***
     * Crée un nouvel utilisateur
     * @param dto dto de l'utilisateur
     * @param bindingResult Validation
     * @return vers la page pour ajouter un utilisateur en cas de problème et vers la méthode utilisateurs sinon
     */
    @PostMapping("/enregistrer")
    public String enregistrer(@Valid @ModelAttribute("dto") DTOInscription dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "enregistrement";
        }
        if (Objects.equals(dto.getPassword1(), dto.getPassword2())) {
            utilisateurService.save(dto);
        } else {
            return "enregistrement";
        }
        return "redirect:/utilisateurs";
    }

    /***
     * Affiche la page contenant tous les utilisateurs
     * @param model le modèle
     * @return la page contenant tous les utilisateurs
     */
    @GetMapping("/utilisateurs")
    public String utilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getUtils();
        model.addAttribute("utilisateurs", utilisateurs);
        List<Utilisateur> comms = utilisateurService.getComms();
        model.addAttribute("comms", comms);
        List<Utilisateur> admins = utilisateurService.getAdmins();
        model.addAttribute("admins", admins);
        return "utilisateurs";
    }

    /***
     * Affiche la page permettant de modifier un utilisateur
     * @param id id de l'utilisateur à modifier
     * @param model le modèle
     * @param session la session
     * @return la page permettant de modifier un utilisateur
     */
    @GetMapping("/modifierUtilisateur/{id}")
    public String modifierUtilisateur(@PathVariable int id, Model model, HttpSession session) {
        // store l'id de l'utilisateur à modifier pour un accès plus facile plus tard
        session.setAttribute("userAModifier", id);
        Utilisateur utilisateur = utilisateurService.findById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "modifier-utilisateur";
    }

    /***
     * Modifie le role d'un utilisateur
     * @param session la session
     * @param request la requête
     * @return vers la méthode utilisateurs
     */
    @PostMapping("/modifierRole")
    public String modifierRole(HttpSession session, HttpServletRequest request) {
        String roleS = request.getParameter("role");
        int role = Integer.parseInt(roleS);
        int id = (int) session.getAttribute("userAModifier");
        utilisateurService.modifyRole(id, role);
        return "redirect:/utilisateurs";
    }

    /***
     * Modifie le mot de passe d'un utilisateur
     * @param session la session
     * @param request la requête
     * @return vers la méthode utilisateurs
     */
    @PostMapping("/modifierMDP")
    public String modifierMDP(HttpSession session, HttpServletRequest request) {
        String p1 = request.getParameter("password1");
        String p2 = request.getParameter("password2");
        int id = (int) session.getAttribute("userAModifier");
        utilisateurService.modifyPasswordAdmin(id, p1, p2);
        return "redirect:/utilisateurs";
    }

    /***
     * Supprime un utilisateur
     * @param id id de l'utilisateur à supprimer
     * @param model le modèle
     * @return vers la méthode utilisateurs
     */
    @GetMapping("supprimerUtilisateur/{id}")
    public String supprimerUtilisateur(@PathVariable int id, Model model) {
        utilisateurService.deleteById(id);
        return "redirect:/utilisateurs";
    }
}
