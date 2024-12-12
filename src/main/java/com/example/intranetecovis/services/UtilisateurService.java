package com.example.intranetecovis.services;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.repositories.IUtilisateurRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UtilisateurService {
    // déclaration des variables de classe
    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RoleService roleService;

    private List<Role> roles (int role) {
        List<Role> roles = new ArrayList<>();
        if (role == 1) {
            roles.add(roleService.findById(1));
        }
        if (role == 2 || role == 1) {
            roles.add(roleService.findById(2));
        }
        if (role == 3 || role == 2 || role == 1) {
            roles.add(roleService.findById(3));
        }
        return roles;
    }

    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.roleService = roleService;
    }

    public void save(DTOInscription dto) {
        Utilisateur u = new Utilisateur();
        u.setPassword(passwordEncoder.encode(dto.getPassword1()));
        // Récupération du rôle USER pour l’assigner à un nouvel utilisateur
        u.setUsername(dto.getUsername());
        u.setPrenom(dto.getPrenom());
        u.setNom(dto.getNom());
        u.setEmail(dto.getEmail());
        u.setRoles(roles(dto.getRole()));
        utilisateurRepository.save(u);
    }

    public void modifyRole(int id, int role) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        u.setRoles(roles(role));
        utilisateurRepository.save(u);
    }

    public void modifyPasswordAdmin(int id, String password1, String password2) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        if (Objects.equals(password1, password2)) {
            u.setPassword(passwordEncoder.encode(password1));
            utilisateurRepository.save(u);
        }
        utilisateurRepository.save(u);
    }

    public void modifyPasswordCompte(int id, String oldPassword, String password1, String password2) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        if (passwordEncoder.matches(oldPassword, u.getPassword()) && Objects.equals(password1, password2)) {
            u.setPassword(passwordEncoder.encode(password1));
            utilisateurRepository.save(u);
        }
    }

    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findUtilisateurByUsername(username);
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur findById(int id) {
        return utilisateurRepository.findById(id).get();
    }

    public void deleteById(int id) {
        utilisateurRepository.deleteById(id);
    }
}
