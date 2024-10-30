package com.backendWebshop.backendWebshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import com.backendWebshop.backendWebshop.model.Product;
import com.backendWebshop.backendWebshop.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(ProductController.class) // Använder WebMvcTest för att testa web-layer
@ExtendWith(MockitoExtension.class)  // Använder MockitoExtension för mockning
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc för att simulera HTTP-anrop

    @MockBean
    private ProductRepository productRepository; // Mockar ProductRepository

    @InjectMocks
    private ProductController productController; // Injicerar den mockade controllern

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Mockar repository-svar
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Product 2");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Utför GET-anropet och förväntade resultat
        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testGetProductById() throws Exception {
        // Mockar repository-svar
        Product product = new Product();
        product.setId("1");
        product.setName("Product 1");
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        // Utför GET-anropet och förväntade resultat
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        // Mockar repository-svar att returnera tomt svar
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        // Utför GET-anropet och förväntade resultat
        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
