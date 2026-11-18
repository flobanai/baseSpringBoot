package com.basespringboot.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CategoryResponse(
    @NotNull @PositiveOrZero Long id,
    @NotBlank String name
) {
    
}
