package com.basespringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.basespringboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
