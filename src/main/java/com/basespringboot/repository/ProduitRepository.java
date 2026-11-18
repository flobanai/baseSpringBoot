package com.basespringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.basespringboot.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {}
