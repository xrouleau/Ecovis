package com.example.intranetecovis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="utilisateurs_nouvelles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "nouvelle_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "nouvelle_id"})
    )
    private List<Nouvelle> nouvelles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
