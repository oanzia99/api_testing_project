package com.sparta.api_testing_project.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BrandResponse {
    @JsonProperty("responseCode")
    private int responseCode;

    @JsonProperty("brands")
    private List<Brand> brands;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
