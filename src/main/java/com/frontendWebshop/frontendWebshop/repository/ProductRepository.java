package com.frontendWebshop.frontendWebshop.repository;

import com.frontendWebshop.frontendWebshop.model.Product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String id);
}

