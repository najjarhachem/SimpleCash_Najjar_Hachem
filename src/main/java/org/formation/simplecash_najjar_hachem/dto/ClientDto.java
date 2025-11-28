package org.formation.simplecash_najjar_hachem.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDto(
                        String nom,

                        String prenom,

                        String adresse,

                        String codePostale,

                        String ville,

                        String telephone) {
}

