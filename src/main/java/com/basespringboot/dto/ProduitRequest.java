package com.basespringboot.dto;
import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProduitRequest(
    @NotBlank String nom,
    @NotNull @Positive BigDecimal prix,
    @NotNull @PositiveOrZero Integer quantite,
    Long categorieId
) {
}