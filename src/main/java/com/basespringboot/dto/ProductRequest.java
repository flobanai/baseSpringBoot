package com.basespringboot.dto;
import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
    @NotBlank String name,
    @NotNull @Positive BigDecimal price,
    @NotNull @PositiveOrZero Integer quantity,
    Long categoryId
) {
}