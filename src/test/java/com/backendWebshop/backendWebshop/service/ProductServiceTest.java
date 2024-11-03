package com.backendWebshop.backendWebshop.service;

import com.backendWebshop.backendWebshop.exception.ResourceNotFoundException;
import com.backendWebshop.backendWebshop.model.Product;
import com.backendWebshop.backendWebshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/*JAG FÅR INTE TILL DET!! JAG SKA ÅTERKOMNMA TILL DENNA NÄR DET ANDRA ÄR KLART!!!*/

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Product 2");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertNotNull(products);
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Product 2", products.get(1).getName());
    }

    @Test
    public void testGetProductById_InvalidId() {
        // Arrange
        String invalidId = "invalidId";

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(invalidId);
        });
    }

}
