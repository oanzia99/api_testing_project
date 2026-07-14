package com.sparta.api_testing_project.integration;

import com.sparta.api_testing_project.client.ApiClient;
import com.sparta.api_testing_project.pojos.ProductResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchIntegrationTest {

    private static ApiClient apiClient;

    @BeforeAll
    static void setup() {
        apiClient = new ApiClient();
    }

    @Test
    @DisplayName("POST /searchProduct with search_product parameter returns 200 and filtered products list")
    void testSearchProductHappyPath() {
        Response response = apiClient.searchProduct("tshirt");
        
        // Assert HTTP Status Code
        Assertions.assertEquals(200, response.getStatusCode(), "HTTP Status should be 200 OK");
        
        // Parse to POJO
        ProductResponse productResponse = response.as(ProductResponse.class);
        
        // Verify data in the response
        Assertions.assertEquals(200, productResponse.getResponseCode(), "Response payload code should be 200");
        Assertions.assertNotNull(productResponse.getProducts(), "Products list should not be null");
        Assertions.assertTrue(productResponse.getProducts().size() > 0, "Products list should contain filtered search results");
        
        // Verify they all contain our search term or match correctly
        for (var p : productResponse.getProducts()) {
            boolean containsSearchTerm = p.getName().toLowerCase().contains("tshirt") 
                    || p.getName().toLowerCase().contains("t-shirt")
                    || p.getCategory().getCategory().toLowerCase().contains("tshirt")
                    || p.getCategory().getCategory().toLowerCase().contains("t-shirt");
            Assertions.assertTrue(containsSearchTerm, "Product name or category should contain search query term 'tshirt': " + p.getName());
        }
    }

    @Test
    @DisplayName("POST /searchProduct without search_product parameter returns 400 Bad Request message")
    void testSearchProductSadPath() {
        Response response = apiClient.searchProductMissingParam();
        
        // Check payload response code (400) and message
        int payloadCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        
        Assertions.assertEquals(400, payloadCode, "Response payload code should be 400");
        Assertions.assertEquals("Bad request, search_product parameter is missing in POST request.", message, "Error message should match missing parameter warning");
    }
}
