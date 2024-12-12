package com.example.intranetecovis.models;

import jakarta.persistence.*;
import jdk.jshell.execution.Util;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<Utilisateur> utilisateurs =  new ArrayList<>();

}
