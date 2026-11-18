package com.basespringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.basespringboot.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
