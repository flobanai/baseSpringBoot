package com.basespringboot.dto;

public record ProduitResponse(Long id, String nom, String categorieNom, Double prix, Integer quantite) {}
