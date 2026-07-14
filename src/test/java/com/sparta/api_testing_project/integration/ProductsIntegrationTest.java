package com.sparta.api_testing_project.integration;

import com.sparta.api_testing_project.client.ApiClient;
import com.sparta.api_testing_project.pojos.ProductResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductsIntegrationTest {

    private static ApiClient apiClient;

    @BeforeAll
    static void setup() {
        apiClient = new ApiClient();
    }

    @Test
    @DisplayName("GET /productsList returns 200 and valid product list")
    void testGetProductsListHappyPath() {
        Response response = apiClient.getAllProducts();
        
        // Assert HTTP Status Code
        Assertions.assertEquals(200, response.getStatusCode(), "HTTP Status should be 200 OK");
        
        // Parse to POJO
        ProductResponse productResponse = response.as(ProductResponse.class);
        
        // Verify data in the response
        Assertions.assertEquals(200, productResponse.getResponseCode(), "Response payload code should be 200");
        Assertions.assertNotNull(productResponse.getProducts(), "Products list should not be null");
        Assertions.assertTrue(productResponse.getProducts().size() > 0, "Products list should contain products");
        
        // Verify details of the first product
        Assertions.assertNotNull(productResponse.getProducts().get(0).getName(), "Product name should not be null");
        Assertions.assertNotNull(productResponse.getProducts().get(0).getBrand(), "Product brand should not be null");
        Assertions.assertTrue(productResponse.getProducts().get(0).getId() > 0, "Product ID should be positive");
    }

    @Test
    @DisplayName("POST /productsList returns 405 method not supported")
    void testPostProductsListSadPath() {
        Response response = apiClient.postAllProducts();
        
        // Check payload response code (405) and message
        int payloadCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        
        Assertions.assertEquals(405, payloadCode, "Response payload code should be 405");
        Assertions.assertEquals("This request method is not supported.", message, "Error message should match");
    }
}
