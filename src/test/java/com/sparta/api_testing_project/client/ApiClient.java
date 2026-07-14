package com.sparta.api_testing_project.client;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class ApiClient {

    private static final String BASE_URL = "https://automationexercise.com/api";

    static {
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    public Response getAllProducts() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .get("/productsList");
    }

    public Response postAllProducts() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .post("/productsList");
    }

    public Response getAllBrands() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .get("/brandsList");
    }

    public Response putAllBrands() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .put("/brandsList");
    }

    public Response searchProduct(String query) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .formParam("search_product", query)
                .when()
                .post("/searchProduct");
    }

    public Response searchProductMissingParam() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .post("/searchProduct");
    }
}
