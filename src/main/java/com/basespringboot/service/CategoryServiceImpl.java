package com.basespringboot.service;

import com.basespringboot.dto.CategoryRequest;
import com.basespringboot.dto.CategoryResponse;
import com.basespringboot.entity.Category;
import com.basespringboot.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    // Spring fournit automatiquement le repository
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> result = new ArrayList<>();
        for (Category c : categories) {
            result.add(new CategoryResponse(c.getId(), c.getName()));
        }
        return result;
    }

    public CategoryResponse findById(Long id) {
        // si la catégorie n'existe pas : erreur 404
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new CategoryResponse(c.getId(), c.getName());
    }

    public CategoryResponse create(CategoryRequest request) {
        Category c = new Category();
        c.setName(request.name());
        c = categoryRepository.save(c); // après save, la catégorie a son id
        return new CategoryResponse(c.getId(), c.getName());
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        c.setName(request.name());
        c = categoryRepository.save(c);
        return new CategoryResponse(c.getId(), c.getName());
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}