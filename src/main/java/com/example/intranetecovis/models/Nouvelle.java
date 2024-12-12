package com.example.intranetecovis.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "nouvelles")
public class Nouvelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    @Column(length = 5000)
    private String contenu;

    @Column(name = "date_publication")
    private Timestamp datePublication;

    private Boolean publie;

    @ToString.Exclude
    @ManyToMany(mappedBy = "nouvelles")
    private List<Utilisateur> utilisateurs =  new ArrayList<>();

    /***
     * Indique quoi faire avant la suppression
     */
    @PreRemove
    private void removeUtilisateur() {
        for (Utilisateur u : utilisateurs) {
            List<Nouvelle> nouvelles = u.getNouvelles();
            nouvelles.remove(this);
            u.setNouvelles(nouvelles);
        }
    }
}
