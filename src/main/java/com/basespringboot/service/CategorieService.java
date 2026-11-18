package com.basespringboot.service;

import com.basespringboot.dto.CategorieRequest;
import com.basespringboot.dto.CategorieResponse;

import java.util.List;

public interface CategorieService {
    List<CategorieResponse> findAll();
    CategorieResponse findById(Long id);
    CategorieResponse create(CategorieRequest requete);
    CategorieResponse update(Long id, CategorieRequest requete);
    void delete(Long id);
}
