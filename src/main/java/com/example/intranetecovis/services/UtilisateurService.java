package com.example.intranetecovis.services;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.repositories.IRoleRepository;
import com.example.intranetecovis.repositories.IUtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RoleService roleService;

    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.roleService = roleService;
    }

    public Utilisateur save(DTOInscription dto) {
        Utilisateur u = new Utilisateur();
        u.setPassword(passwordEncoder.encode(dto.getPassword1()));
        // Récupération du rôle USER pour l’assigner à un nouvel utilisateur
        u.setUsername(dto.getUsername());
        u.setPrenom(dto.getPrenom());
        u.setNom(dto.getNom());
        u.setEmail(dto.getEmail());
        List<Role> roles = new ArrayList<>();
        if (dto.getRole() == 1) {
            roles.add(roleService.findById(1));
        }
        if (dto.getRole() == 2 || dto.getRole() == 1) {
            roles.add(roleService.findById(2));
        }
        if (dto.getRole() == 3 || dto.getRole() == 2 || dto.getRole() == 1) {
            roles.add(roleService.findById(3));
        }
        u.setRoles(roles);
        return utilisateurRepository.save(u);
    }

    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findUtilisateurByUsername(username);
    }
}
