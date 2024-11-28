package com.example.intranetecovis.services;

import com.example.intranetecovis.models.DTOInscription;
import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.repositories.IRoleRepository;
import com.example.intranetecovis.repositories.IUtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private IRoleRepository roleRepository;

    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository, IRoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    public Utilisateur save(DTOInscription utilisateur) {
        Utilisateur user = new Utilisateur();
        // Utilisation du password encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByName("ROLE_USER");
        // Récupération du rôle USER pour l’assigner à un nouvel utilisateur
    /*
        … Gestion du reste du mapping de l’utilisateur
    */
        return utilisateurRepository.save(user);
    }


}
