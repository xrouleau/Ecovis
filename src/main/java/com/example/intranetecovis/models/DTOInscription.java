package com.example.intranetecovis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOInscription {
    @NotNull(message = "Veuillez saisir le nom")
    @Length(min = 2, max = 30, message = "Le nom doit être composé d'un minimum de 2 charatères et d'un maximum de 30")
    private String nom;
    @NotNull(message = "Veuillez saisir le prenom")
    @Length(min = 2, max = 30, message = "Le prénom doit être composé d'un minimum de 2 charatères et d'un maximum de 30")
    private String prenom;
    @NotNull(message = "Veuillez saisir le courriel")
    @Email
    private String email;
    @NotNull(message = "Veuillez saisir le mot de passe")
    @Length(min = 8, max = 30, message = "Le mot de passe doit être composé d'un minimum de 8 charatères et d'un maximum de 30")
    private String password1;
    @NotNull(message = "Veuillez saisir la confirmation du mot de passe")
    @Length(min = 8, max = 30, message = "Le mot de passe doit être composé d'un minimum de 8 charatères et d'un maximum de 30")
    private String password2;
    @NotNull(message = "Veuillez saisir le nom d'utilisateur")
    @Length(min = 5, max = 30, message = "Le nom d'utilisateur doit être composé d'un minimum de 8 charatères et d'un maximum de 30")
    private String username;
}
