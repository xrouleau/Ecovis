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

/***
 * Classe de service des utilisateurs
 */
@Service
public class UtilisateurService {
    // déclaration des variables de classe
    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RoleService roleService;

    /***
     * Méthode privé pour ajouter tous les rôle sous un certain role
     * @param role role le plus haut de l'utilisateur
     * @return liste avec le role le plus haut de l'utilisateur et tous ceux plus bas
     */
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

    /***
     * Constructeur de la classe UtilisateurService
     * @param passwordEncoder encodeur de mot de passe
     * @param utilisateurRepository Repository des utilisateurs
     * @param roleService Service des roles
     */
    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.roleService = roleService;
    }

    /***
     * Crée un nouvel utilisateur
     * @param dto brouillon de l'utilisateur à créer
     */
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

    /***
     * Modifie le role
     * @param id id de l'utilisateur à modifier
     * @param role id du role à mettre
     */
    public void modifyRole(int id, int role) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        u.setRoles(roles(role));
        utilisateurRepository.save(u);
    }

    /***
     * Modifie le mot de passe d'un utilisateur (action par admin)
     * @param id id de l'utilisateur que l'on souhaite modifier
     * @param password1 mot de passe
     * @param password2 confirmation
     */
    public void modifyPasswordAdmin(int id, String password1, String password2) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        if (Objects.equals(password1, password2)) {
            u.setPassword(passwordEncoder.encode(password1));
            utilisateurRepository.save(u);
        }
    }

    /***
     * Modifie le mot de passe d'un utilisateur (action par l'utilisateur)
     * @param id id de l'utilisateur
     * @param oldPassword ancien mot de passe
     * @param password1 nouveau mot de passe
     * @param password2 confirmation
     */
    public void modifyPasswordCompte(int id, String oldPassword, String password1, String password2) {
        Utilisateur u = utilisateurRepository.findById(id).get();
        if (passwordEncoder.matches(oldPassword, u.getPassword()) && Objects.equals(password1, password2)) {
            u.setPassword(passwordEncoder.encode(password1));
            utilisateurRepository.save(u);
        }
    }

    /***
     * Trouve un utilisateur selon son username
     * @param username username de l'utilisateur à trouver
     * @return l'utilisateur avec le username
     */
    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findUtilisateurByUsername(username);
    }

    /***
     * Retourne tous les users
     * @return tous les utilisateurs
     */
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    /***
     * trouve un utilisateur selon son id
     * @param id id de l'utilisateur à trouver
     * @return utilisateur avec le id
     */
    public Utilisateur findById(int id) {
        return utilisateurRepository.findById(id).get();
    }

    /***
     * Supprimer un utilisateur selon son id
     * @param id id de l'utilisateur à supprimer
     */
    public void deleteById(int id) {
        utilisateurRepository.deleteById(id);
    }

    /***
     * Retourne une liste de tous les administrateurs
     * @return une liste de tous les administrateurs
     */
    public List<Utilisateur> getAdmins() {
        return utilisateurRepository.findUtilisateurByRolesContaining(roleService.findById(1));
    }

    /***
     * Retourne une liste de tous les communications
     * @return une liste de tous les communications
     */
    public List<Utilisateur> getComms() {
        return utilisateurRepository.findUtilisateurByRolesContainingAndRolesNotContaining(roleService.findById(2), roleService.findById(1));
    }

    /***
     * Retourne une liste de tous les utilisateurs normaux
     * @return une liste de tous les utilisateurs normaux
     */
    public List<Utilisateur> getUtils() {
        return utilisateurRepository.findUtilisateurByRolesContainingAndRolesNotContaining(roleService.findById(3), roleService.findById(2));
    }

    /***
     * Trouve tous les comms d'une liste
     * @param utilisateurs liste d'utilisateurs
     * @return tous les comms d'une liste
     */
    public List<Utilisateur> getCommsDeListe(List<Utilisateur> utilisateurs) {
        List<Utilisateur> comms = new ArrayList<>();
        for (Utilisateur u : utilisateurs) {
            if (u.getRoles().contains(roleService.findById(2)) && !u.getRoles().contains(roleService.findById(1))) {
                comms.add(u);
            }
        }
        return comms;
    }

    public void sauvegarder(Utilisateur u) {
        utilisateurRepository.save(u);
    }
}
