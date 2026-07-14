package com.sparta.api_testing_project.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {
    @JsonProperty("id")
    private int id;

    @JsonProperty("brand")
    private String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
