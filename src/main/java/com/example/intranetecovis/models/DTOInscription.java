package com.example.intranetecovis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOInscription {
    @NotNull(message = "Veuillez saisir le nom")
    @Min(value = 2, message = "Le nom doit être composé d'au minimum 2 charatères")
    private String nom;
    @NotNull(message = "Veuillez saisir le prenom")
    @Min(value = 2, message = "Le prenom doit être composé d'au minimum 2 charatères")
    private String prenom;
    @NotNull(message = "Veuillez saisir le courriel")
    @Email
    private String email;
    @NotNull(message = "Veuillez saisir le mot de passe")
    @Min(value = 8, message = "Le mot de passe doit être composé d'au minimum 8 charatères")
    private String password1;
    @NotNull(message = "Veuillez saisir la confirmation du mot de passe")
    @Min(value = 8, message = "Le mot de passe doit être composé d'au minimum 8 charatères")
    private String password2;
    @NotNull(message = "Veuillez saisir le nom d'utilisateur")
    @Min(value = 5, message = "Le nom d'utilisateur doit être composé d'au minimum 5 charatères")
    private String username;
    @NotNull
    private List<Role> roles = new ArrayList<>();
}
