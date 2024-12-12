package com.example.intranetecovis.repositories;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INouvelleRepository extends JpaRepository<Nouvelle, Integer> {

    List<Nouvelle> findByUtilisateursContainsOrderByDatePublication(Utilisateur utilisateur);

    List<Nouvelle> findByPublieEqualsOrderByDatePublicationDesc(Boolean publie);
}
