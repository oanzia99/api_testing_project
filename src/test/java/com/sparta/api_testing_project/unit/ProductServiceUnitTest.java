package com.sparta.api_testing_project.unit;

import com.sparta.api_testing_project.client.ApiClient;
import com.sparta.api_testing_project.pojos.Product;
import com.sparta.api_testing_project.pojos.ProductResponse;
import com.sparta.api_testing_project.service.ProductService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    private ApiClient apiClient;

    @Mock
    private Response mockResponse;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(apiClient);
    }

    @Test
    @DisplayName("Filter products by brand should filter correctly case-insensitive")
    void testFilterProductsByBrand() {
        // Arrange
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Product A");
        p1.setBrand("Polo");

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Product B");
        p2.setBrand("H&M");

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Product C");
        p3.setBrand("polo"); // lower case

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(Arrays.asList(p1, p2, p3));

        Mockito.when(apiClient.getAllProducts()).thenReturn(mockResponse);
        Mockito.when(mockResponse.getStatusCode()).thenReturn(200);
        Mockito.when(mockResponse.as(ProductResponse.class)).thenReturn(productResponse);

        // Act
        List<Product> poloProducts = productService.filterProductsByBrand("Polo");

        // Assert
        Assertions.assertEquals(2, poloProducts.size());
        Assertions.assertEquals("Product A", poloProducts.get(0).getName());
        Assertions.assertEquals("Product C", poloProducts.get(1).getName());
    }

    @Test
    @DisplayName("Get products cheaper than should return products under max price")
    void testGetProductsCheaperThan() {
        // Arrange
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Product A");
        p1.setPrice("Rs. 500"); // 500.0

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Product B");
        p2.setPrice("Rs. 1200"); // 1200.0

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Product C");
        p3.setPrice("Rs. 300"); // 300.0

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(Arrays.asList(p1, p2, p3));

        Mockito.when(apiClient.getAllProducts()).thenReturn(mockResponse);
        Mockito.when(mockResponse.getStatusCode()).thenReturn(200);
        Mockito.when(mockResponse.as(ProductResponse.class)).thenReturn(productResponse);

        // Act
        List<Product> cheapProducts = productService.getProductsCheaperThan(600.0);

        // Assert
        Assertions.assertEquals(2, cheapProducts.size());
        Assertions.assertEquals("Product A", cheapProducts.get(0).getName());
        Assertions.assertEquals("Product C", cheapProducts.get(1).getName());
    }
}
