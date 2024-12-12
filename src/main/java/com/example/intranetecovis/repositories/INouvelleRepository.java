package com.example.intranetecovis.repositories;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INouvelleRepository extends JpaRepository<Nouvelle, Integer> {

    // Toutes les nouvelles d'un utilisateur en ordre décroissant de date
    List<Nouvelle> findByUtilisateursContainsOrderByDatePublication(Utilisateur utilisateur);

    // Toutes les nouvelles publiées en ordre décroissant de date
    List<Nouvelle> findByPublieEqualsOrderByDatePublicationDesc(Boolean publie);

    // Toutes les nouvelles en ordre décroissant de date
    List<Nouvelle> findAllByOrderByDatePublicationDesc();
}
