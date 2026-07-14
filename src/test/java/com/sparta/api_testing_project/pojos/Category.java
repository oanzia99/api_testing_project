package com.sparta.api_testing_project.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty("usertype")
    private UserType usertype;

    @JsonProperty("category")
    private String category;

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
