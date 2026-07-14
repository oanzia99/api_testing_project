package com.sparta.api_testing_project.service;

import com.sparta.api_testing_project.client.ApiClient;
import com.sparta.api_testing_project.pojos.Product;
import com.sparta.api_testing_project.pojos.ProductResponse;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ApiClient apiClient;

    public ProductService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Product> filterProductsByBrand(String brandName) {
        if (brandName == null) {
            return new ArrayList<>();
        }
        Response response = apiClient.getAllProducts();
        if (response.getStatusCode() != 200) {
            return new ArrayList<>();
        }
        ProductResponse productResponse = response.as(ProductResponse.class);
        List<Product> filtered = new ArrayList<>();
        if (productResponse.getProducts() != null) {
            for (Product p : productResponse.getProducts()) {
                if (brandName.equalsIgnoreCase(p.getBrand())) {
                    filtered.add(p);
                }
            }
        }
        return filtered;
    }

    public List<Product> getProductsCheaperThan(double maxPrice) {
        Response response = apiClient.getAllProducts();
        if (response.getStatusCode() != 200) {
            return new ArrayList<>();
        }
        ProductResponse productResponse = response.as(ProductResponse.class);
        List<Product> filtered = new ArrayList<>();
        if (productResponse.getProducts() != null) {
            for (Product p : productResponse.getProducts()) {
                if (p.getPriceAsDouble() < maxPrice) {
                    filtered.add(p);
                }
            }
        }
        return filtered;
    }
}
