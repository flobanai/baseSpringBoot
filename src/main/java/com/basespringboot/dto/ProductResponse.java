package com.basespringboot.dto;

public record ProductResponse(Long id, String name, String categoryName, Double price, Integer quantity) {}