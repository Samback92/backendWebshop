package com.backendWebshop.backendWebshop.service;


import com.backendWebshop.backendWebshop.model.Product;
import com.backendWebshop.backendWebshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceTest {

    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void getAllProducts_shouldReturnEmptyList_whenNoProductsExist() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
    
        List<Product> result = productService.getAllProducts();
    
        assertTrue((result).isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    void getAllProducts_shouldReturnListWithOneProduct_whenOnlyOneProductInRepository() {
        // Arrange
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(List.of(product));
    
        // Act
        List<Product> result = productService.getAllProducts();
    
        // Assert
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getAllProducts_shouldReturnListWithMultipleProducts_whenMultipleProductsExist() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(
            new Product("1", "Product 1", "Description 1", 10.0),
            new Product("2", "Product 2", "Description 2", 20.0),
            new Product("3", "Product 3", "Description 3", 30.0)
        );
        when(productRepository.findAll()).thenReturn(expectedProducts);
    
        // Act
        List<Product> actualProducts = productService.getAllProducts();
    
        // Assert
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository, times(1)).findAll();
    }

}
