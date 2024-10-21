package com.backendWebshop.backendWebshop.service;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import com.backendWebshop.backendWebshop.exception.ResourceNotFoundException;
import com.backendWebshop.backendWebshop.model.Product;
import com.backendWebshop.backendWebshop.repository.ProductRepository;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ResourceNotFoundException("Invalid Product ID");
        }
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
