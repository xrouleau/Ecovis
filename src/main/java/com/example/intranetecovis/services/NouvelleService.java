package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.repositories.INouvelleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NouvelleService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private INouvelleRepository nouvelleRepository;
    private UtilisateurService utilisateurService;
    private HttpSession session;

    public NouvelleService(INouvelleRepository nouvelleRepository, UtilisateurService utilisateurService, HttpSession session) {
        this.nouvelleRepository = nouvelleRepository;
        this.utilisateurService = utilisateurService;
        this.session = session;
    }

    public List<Nouvelle> getAll() {
        return nouvelleRepository.findByPublieEqualsOrderByDatePublicationDesc(true);
    }

    public void save(String titre, Utilisateur u) {
        Nouvelle nouvelle = new Nouvelle();
        nouvelle.setTitre(titre);
        nouvelle.setPublie(false);
        nouvelle.setContenu("");
        nouvelle.setDatePublication(new Timestamp(System.currentTimeMillis()));
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(u);
        nouvelle.setUtilisateurs(utilisateurs);
        nouvelleRepository.save(nouvelle);
    }

    public Nouvelle findById(int id) {
        return nouvelleRepository.findById(id).get();
    }

    public void deleteById(int id) {
        nouvelleRepository.deleteById(id);
    }

    public void retirer(int id) {
        Nouvelle nouvelle = findById(id);
        nouvelle.setPublie(false);
        nouvelleRepository.save(nouvelle);
    }

    public void publier(int id) {
        Nouvelle nouvelle = findById(id);
        nouvelle.setPublie(true);
        nouvelleRepository.save(nouvelle);
    }

    public List<Nouvelle> findByUtilisateurContains(Utilisateur utilisateur) {
        return nouvelleRepository.findByUtilisateursContainsOrderByDatePublication(utilisateur);
    }

    public void retirerUtilisateur(int nouvelleId, int utilisateurId) {
        Utilisateur u = utilisateurService.findById(utilisateurId);
        Nouvelle n = findById(nouvelleId);
        List<Utilisateur> utilisateurs = n.getUtilisateurs();
        System.out.println(utilisateurs.size());
        utilisateurs.remove(u);
        n.setUtilisateurs(utilisateurs);
        System.out.println(n.getUtilisateurs().size());
        nouvelleRepository.save(n);
    }

    public void ajouterUtilisateur(int nouvelleId, String utilisateurUsername) {
        Utilisateur u = utilisateurService.findByUsername(utilisateurUsername);
        if (u == null) {
            throw new UsernameNotFoundException("Nom d’utilisateur invalide");
        }
        Nouvelle n = findById(nouvelleId);
        List<Utilisateur> utilisateurs = n.getUtilisateurs();
        System.out.println(utilisateurs.size());
        utilisateurs.add(u);
        n.setUtilisateurs(utilisateurs);
        System.out.println(n.getUtilisateurs().size());
        nouvelleRepository.save(n);
    }
}
