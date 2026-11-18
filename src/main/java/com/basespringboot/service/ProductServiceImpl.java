package com.basespringboot.service;

import com.basespringboot.dto.ProductRequest;
import com.basespringboot.dto.ProductResponse;
import com.basespringboot.entity.Category;
import com.basespringboot.entity.Product;
import com.basespringboot.repository.CategoryRepository;
import com.basespringboot.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // Spring fournit automatiquement les deux repositories
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Convertit un Product (base de données) en ProductResponse (ce qu'on renvoie au client)
    private ProductResponse toResponse(Product p) {
        String categoryName = null;
        if (p.getCategory() != null) { // la catégorie est facultative
            categoryName = p.getCategory().getName();
        }
        // le prix est un BigDecimal dans Product mais un Double dans ProductResponse
        return new ProductResponse(p.getId(), p.getName(), categoryName,
                p.getPrice().doubleValue(), p.getQuantity());
    }

    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> result = new ArrayList<>();
        for (Product p : products) {
            result.add(toResponse(p));
        }
        return result;
    }

    public ProductResponse findById(Long id) {
        // si le produit n'existe pas : erreur 404
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toResponse(p);
    }

    public ProductResponse create(ProductRequest request) {
        Product p = new Product();
        p.setName(request.name());
        p.setPrice(request.price());
        p.setQuantity(request.quantity());

        // on associe la catégorie seulement si le client en a donné une
        if (request.categoryId() != null) {
            Category c = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            p.setCategory(c);
        }

        p = productRepository.save(p);
        return toResponse(p);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        p.setName(request.name());
        p.setPrice(request.price());
        p.setQuantity(request.quantity());

        if (request.categoryId() != null) {
            Category c = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            p.setCategory(c);
        } else {
            p.setCategory(null); // pas de catégorie donnée : on retire l'ancienne
        }

        p = productRepository.save(p);
        return toResponse(p);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}