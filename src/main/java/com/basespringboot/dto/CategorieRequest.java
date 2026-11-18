package com.basespringboot.dto;
import jakarta.validation.constraints.NotBlank;

public record CategorieRequest(
    @NotBlank String nom
) {
    
}
