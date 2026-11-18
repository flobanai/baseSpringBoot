package com.basespringboot.service;

import com.basespringboot.dto.CategoryRequest;
import com.basespringboot.dto.CategoryResponse;
import com.basespringboot.entity.Category;
import com.basespringboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TEMPORARY minimal implementation - to be replaced by Thibault's services
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = get(id);
        category.setName(request.name());
        return toResponse(repository.save(category));
    }

    @Override
    public void delete(Long id) {
        repository.delete(get(id));
    }

    private Category get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
