package com.sparta.api_testing_project.integration;

import com.sparta.api_testing_project.client.ApiClient;
import com.sparta.api_testing_project.pojos.BrandResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BrandsIntegrationTest {

    private static ApiClient apiClient;

    @BeforeAll
    static void setup() {
        apiClient = new ApiClient();
    }

    @Test
    @DisplayName("GET /brandsList returns 200 and valid brand list")
    void testGetBrandsListHappyPath() {
        Response response = apiClient.getAllBrands();
        
        // Assert HTTP Status Code
        Assertions.assertEquals(200, response.getStatusCode(), "HTTP Status should be 200 OK");
        
        // Parse to POJO
        BrandResponse brandResponse = response.as(BrandResponse.class);
        
        // Verify data in the response
        Assertions.assertEquals(200, brandResponse.getResponseCode(), "Response payload code should be 200");
        Assertions.assertNotNull(brandResponse.getBrands(), "Brands list should not be null");
        Assertions.assertTrue(brandResponse.getBrands().size() > 0, "Brands list should contain brands");
        
        // Verify details of the first brand
        Assertions.assertNotNull(brandResponse.getBrands().get(0).getBrand(), "Brand name should not be null");
        Assertions.assertTrue(brandResponse.getBrands().get(0).getId() > 0, "Brand ID should be positive");
    }

    @Test
    @DisplayName("PUT /brandsList returns 405 method not supported")
    void testPutBrandsListSadPath() {
        Response response = apiClient.putAllBrands();
        
        // Check payload response code (405) and message
        int payloadCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        
        Assertions.assertEquals(405, payloadCode, "Response payload code should be 405");
        Assertions.assertEquals("This request method is not supported.", message, "Error message should match");
    }
}
