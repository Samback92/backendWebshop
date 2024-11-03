package com.backendWebshop.backendWebshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backendWebshop.backendWebshop.model.Product;
import com.backendWebshop.backendWebshop.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        log.info("Fetching product with id {}", id);
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // @PostMapping
    // public Product createProduct(@RequestBody Product product) {
    //     return productRepository.saveProduct(product);
    // }

    // @PutMapping("/{id}")
    // public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
    //     product.setId(id);
    //     return productRepository.saveProduct(product);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteProduct(@PathVariable String id) {
    //     productRepository.deleteProduct(id);
    // }

}
