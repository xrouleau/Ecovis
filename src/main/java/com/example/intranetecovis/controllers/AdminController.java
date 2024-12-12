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

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {
    private RoleService roleService;
    private UtilisateurService utilisateurService;

    public AdminController(RoleService roleService, UtilisateurService utilisateurService) {
        this.roleService = roleService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/enregistrer")
    public String enregistrer(Model model) {
        DTOInscription dto = new DTOInscription();
        model.addAttribute("dto", dto);
        return "enregistrement";
    }

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

    @GetMapping("/utilisateurs")
    public String utilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "utilisateurs";
    }

    @GetMapping("/modifierUtilisateur/{id}")
    public String modifierUtilisateur(@PathVariable int id, Model model, HttpSession session) {
        session.setAttribute("userAModifier", id);
        Utilisateur utilisateur = utilisateurService.findById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "modifier-utilisateur";
    }

    @PostMapping("/modifierRole")
    public String modifierRole(HttpSession session, HttpServletRequest request) {
        String roleS = request.getParameter("role");
        int role = Integer.parseInt(roleS);
        int id = (int) session.getAttribute("userAModifier");
        utilisateurService.modifyRole(id, role);
        return "redirect:/utilisateurs";
    }

    @PostMapping("/modifierMDP")
    public String modifierMDP(HttpSession session, HttpServletRequest request) {
        String p1 = request.getParameter("password1");
        String p2 = request.getParameter("password2");
        int id = (int) session.getAttribute("userAModifier");
        utilisateurService.modifyPasswordAdmin(id, p1, p2);
        return "redirect:/utilisateurs";
    }

    @GetMapping("supprimerUtilisateur/{id}")
    public String supprimerUtilisateur(@PathVariable int id, Model model) {
        utilisateurService.deleteById(id);
        return "redirect:/utilisateurs";
    }
}
