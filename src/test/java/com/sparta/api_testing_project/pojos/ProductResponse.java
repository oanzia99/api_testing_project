package com.sparta.api_testing_project.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ProductResponse {
    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("products")
    private List<Product> products;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
