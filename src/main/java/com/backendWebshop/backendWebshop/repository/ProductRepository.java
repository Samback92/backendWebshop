package com.backendWebshop.backendWebshop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backendWebshop.backendWebshop.model.Product;


public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String id);
}

