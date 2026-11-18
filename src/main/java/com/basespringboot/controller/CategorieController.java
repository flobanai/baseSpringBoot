package com.basespringboot.controller;

import com.basespringboot.dto.CategorieRequest;
import com.basespringboot.dto.CategorieResponse;
import com.basespringboot.service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService service;

    public CategorieController(CategorieService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategorieResponse> toutes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategorieResponse une(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CategorieResponse creer(@Valid @RequestBody CategorieRequest requete) {
        return service.create(requete);
    }

    @PutMapping("/{id}")
    public CategorieResponse modifier(@PathVariable Long id, @Valid @RequestBody CategorieRequest requete) {
        return service.update(id, requete);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        service.delete(id);
    }
}
