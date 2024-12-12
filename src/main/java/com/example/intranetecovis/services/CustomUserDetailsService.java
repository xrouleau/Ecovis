package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/***
 * Classe qui s'occupe du login d'un utilisateur
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    // déclaration des variables de classe
    private UtilisateurService utilisateurService;
    private HttpSession session;

    /***
     * Constructeur de la classe CustomUserDetailsService
     * @param utilisateurService Service de l'utilisateur
     * @param session la sesson
     */
    public CustomUserDetailsService(UtilisateurService utilisateurService, HttpSession session) {
        this.utilisateurService = utilisateurService;
        this.session = session;
    }


    /***
     * S'occupe du login d'un utilisateur
     * @param username username de l'utilisateur
     * @return l'utilisateur authentifié
     * @throws UsernameNotFoundException erreur
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nom d’utilisateur ou mot de passe invalide");
        }
        else {
            User authUser = new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
            session.setAttribute("user", user);
            return authUser;
        }

    }
}

