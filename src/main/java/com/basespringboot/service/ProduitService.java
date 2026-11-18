package com.basespringboot.service;

import com.basespringboot.dto.ProduitRequest;
import com.basespringboot.dto.ProduitResponse;

import java.util.List;

public interface ProduitService {
    List<ProduitResponse> findAll();
    List<ProduitResponse> findByCategorie(Long categorieId);
    ProduitResponse findById(Long id);
    ProduitResponse create(ProduitRequest requete);
    ProduitResponse update(Long id, ProduitRequest requete);
    void delete(Long id);
}
