package com.sparta.api_testing_project.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private String price;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("category")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Helper to get raw price as double (e.g. "Rs. 500" -> 500.0)
    public double getPriceAsDouble() {
        if (price == null) return 0.0;
        String cleanPrice = price.replaceAll("Rs.\\s*", "").trim();
        try {
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
