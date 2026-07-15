package com.sparta.endpointtesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServerUpTest {
    private static Response response;

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("https://automationexercise.com")
                .when()
                .get("api/productsList")
                .then()
                .log().all()
                .extract().response();
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

}
