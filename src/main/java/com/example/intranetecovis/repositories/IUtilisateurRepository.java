package com.example.intranetecovis.repositories;

import com.example.intranetecovis.models.Role;
import com.example.intranetecovis.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Trouve l'objet Utilisateur avec le username donné en paramètre
    Utilisateur findUtilisateurByUsername(String username);

    // Trouve les utilisateurs admins
    List<Utilisateur> findUtilisateurByRolesContaining(Role role);

    // Trouve les utilisateurs qui ont un role max (Exemple un utilisateur en communication, sans renvoyer d'admins)
    List<Utilisateur> findUtilisateurByRolesContainingAndRolesNotContaining(Role role, Role role2);
}
