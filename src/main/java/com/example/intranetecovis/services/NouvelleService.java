package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.models.Utilisateur;
import com.example.intranetecovis.repositories.INouvelleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/***
 * Service des nouvelles
 */
@Service
public class NouvelleService {

    // déclaration des variables de classe
    private INouvelleRepository nouvelleRepository;
    private UtilisateurService utilisateurService;
    private RoleService roleService;

    /***
     * Constructeur de la classe NouvelleService
     * @param nouvelleRepository Repository des nouvelles
     * @param utilisateurService Service des utilisateurs
     * @param roleService Service des roles
     */
    public NouvelleService(INouvelleRepository nouvelleRepository, UtilisateurService utilisateurService, RoleService roleService) {
        this.nouvelleRepository = nouvelleRepository;
        this.utilisateurService = utilisateurService;
        this.roleService = roleService;
    }

    /***
     * retourne toutes les nouvelles publiées en ordre décroissant selon la date
     * @return toutes les nouvelles publiées en ordre décroissant selon la date
     */
    public List<Nouvelle> getAllPublie() {
        return nouvelleRepository.findByPublieEqualsOrderByDatePublicationDesc(true);
    }

    /***
     * retourne toutes les nouvelles en ordre décroissant selon la date
     * @return toutes les nouvelles en ordre décroissant selon la date
     */
    public List<Nouvelle> getAll() {
        return nouvelleRepository.findAllByOrderByDatePublicationDesc();
    }

    /***
     * Sauvegarde une nouvelle nouvelle
     * @param titre titre de la nouvelle
     * @param u objet Utilisateur de l'auteur
     */
    public void save(String titre, Utilisateur u) {
        Nouvelle nouvelle = new Nouvelle();
        nouvelle.setTitre(titre);
        nouvelle.setPublie(false);
        nouvelle.setContenu("");
        nouvelle.setDatePublication(new Timestamp(System.currentTimeMillis()));
        List<Utilisateur> utilisateurs = utilisateurService.getAdmins();
        if (u.getRoles().contains(roleService.findById(1))) {
            nouvelle.setUtilisateurs(utilisateurs);
        } else {
            utilisateurs.add(u);
            nouvelle.setUtilisateurs(utilisateurs);
        }
        nouvelleRepository.save(nouvelle);
    }

    /***
     * Trouve une nouvelle selon son ID
     * @param id id de la nouvelle à trouver
     * @return une nouvelle
     */
    public Nouvelle findById(int id) {
        return nouvelleRepository.findById(id).get();
    }

    /***
     * Supprime une nouvelle selon son ID
     * @param id id de la nouvelle à supprimer
     */
    public void deleteById(int id) {
        nouvelleRepository.deleteById(id);
    }

    /***
     * Met une nouvelle en mode brouillon
     * @param id id de la nouvelle à mettre en brouillon
     */
    public void retirer(int id) {
        Nouvelle nouvelle = findById(id);
        nouvelle.setPublie(false);
        nouvelleRepository.save(nouvelle);
    }

    /***
     * Publie une nouvelle
     * @param id id de la nouvelle à publier
     */
    public void publier(int id) {
        Nouvelle nouvelle = findById(id);
        nouvelle.setPublie(true);
        nouvelleRepository.save(nouvelle);
    }

    /***
     * Trouve toutes les nouvelles d'un utilisateur en ordre décroissant selon la date
     * @param utilisateur utilisateur que l'on souhaite trouver ses nouvelles
     * @return toutes les nouvelles d'un utilisateur en ordre décroissant selon la date
     */
    public List<Nouvelle> findByUtilisateurContains(Utilisateur utilisateur) {
        return nouvelleRepository.findByUtilisateursContainsOrderByDatePublication(utilisateur);
    }

    /***
     * Retirer un utilisateur de la liste d'auteur d'une nouvelle
     * @param nouvelleId id de la nouvelle en question
     * @param utilisateurId id de l'utilisateur à retirer
     */
    public void retirerUtilisateur(int nouvelleId, int utilisateurId) {
        Utilisateur u = utilisateurService.findById(utilisateurId);
        Nouvelle n = findById(nouvelleId);
        List<Utilisateur> utilisateurs = n.getUtilisateurs();
        System.out.println(utilisateurs.size());
        utilisateurs.remove(u);
        n.setUtilisateurs(utilisateurs);
        System.out.println(n.getUtilisateurs().size());
        try {
            nouvelleRepository.save(n);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        u.getNouvelles().remove(n);
        try {
            utilisateurService.sauvegarder(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * Ajouter un utilisateur à la liste d'auteur d'une nouvelle
     * @param nouvelleId id de la nouvelle en question
     * @param utilisateurUsername username de l'utilisateur à ajouter
     */
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
        try {
            nouvelleRepository.save(n);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        u.getNouvelles().add(n);
        try {
            utilisateurService.sauvegarder(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    /***
     * Modifie une nouvelle
     * @param id id de la nouvelle à modifier
     * @param titre nouveau titre
     * @param contenu nouveau contenu
     */
    public void modifierNouvelle(int id, String titre, String contenu) {
        Nouvelle nouvelle = findById(id);
        nouvelle.setTitre(titre);
        nouvelle.setContenu(contenu);
        nouvelleRepository.save(nouvelle);
    }
}
