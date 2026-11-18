package com.basespringboot.controller;

import com.basespringboot.dto.ProduitRequest;
import com.basespringboot.dto.ProduitResponse;
import com.basespringboot.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProduitResponse> tous() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProduitResponse un(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProduitResponse creer(@Valid @RequestBody ProduitRequest requete) {
        return service.create(requete);
    }

    @PutMapping("/{id}")
    public ProduitResponse modifier(@PathVariable Long id, @Valid @RequestBody ProduitRequest requete) {
        return service.update(id, requete);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        service.delete(id);
    }
}
