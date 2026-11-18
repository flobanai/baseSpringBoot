package com.basespringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.basespringboot.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {}
