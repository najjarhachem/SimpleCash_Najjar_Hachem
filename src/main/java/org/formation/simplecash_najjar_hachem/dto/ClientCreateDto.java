package org.formation.simplecash_najjar_hachem.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateDto (

    @NotBlank(message = "nom can not be empty")
    String nom,

    @NotBlank(message = "prenom can not be empty")
    String prenom,

    @NotBlank(message = "address can not be empty")
    String adresse,

    @NotBlank(message = "code postale can not be empty")
    String codePostale,

    @NotBlank(message = "ville can not be empty")
    String ville,

    @NotBlank(message = "telephone can not be empty")
    String telephone

    ){}

