package com.example.intranetecovis.repositories;

import com.example.intranetecovis.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
