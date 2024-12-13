package com.example.intranetecovis.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String username;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateurs_nouvelles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "nouvelle_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "nouvelle_id"})
    )
    private Set<Nouvelle> nouvelles = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateurs_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "role_id"})
    )
    private Set<Role> roles = new HashSet<>();

    /***
     * Indique quoi faire avant la suppression
     */
    @PreRemove
    private void removeUtilisateur() {
        for (Nouvelle nouvelle : nouvelles) {
            List<Utilisateur> utilisateurs = nouvelle.getUtilisateurs();
            utilisateurs.remove(this);
            nouvelle.setUtilisateurs(utilisateurs);
        }
    }
}
