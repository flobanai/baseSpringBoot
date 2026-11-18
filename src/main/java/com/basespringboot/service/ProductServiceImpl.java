package com.basespringboot.service;

import com.basespringboot.dto.ProductRequest;
import com.basespringboot.dto.ProductResponse;
import com.basespringboot.entity.Category;
import com.basespringboot.entity.Product;
import com.basespringboot.repository.CategoryRepository;
import com.basespringboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TEMPORARY minimal implementation - to be replaced by Thibault's services
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        apply(product, request);
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = get(id);
        apply(product, request);
        return toResponse(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(get(id));
    }

    private void apply(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow();
        product.setCategory(category);
    }

    private Product get(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(),
                product.getCategory().getName(), product.getPrice().doubleValue(), product.getQuantity());
    }
}
