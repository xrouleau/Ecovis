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
@Table(name = "nouvelles")
public class Nouvelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private String contenu;

    @Column(name = "date_publication")
    private Date datePublication;

    @ManyToMany(mappedBy = "nouvelles")
    private List<Utilisateur> utilisateurs =  new ArrayList<>();

}
