package com.example.intranetecovis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateurs_nouvelles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "nouvelle_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "nouvelle_id"})
    )
    private List<Nouvelle> nouvelles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utilisateurs_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "role_id"})
    )
    private List<Role> roles = new ArrayList<>();

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
