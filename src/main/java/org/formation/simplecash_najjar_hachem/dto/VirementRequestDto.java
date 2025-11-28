package org.formation.simplecash_najjar_hachem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VirementRequestDto(@NotNull
                                   Long idSource,

                                 @NotNull
                                   Long idDestination,

                                 @Min(value = 1, message = "Le montant doit être positif")
                                   float montant) {
}
