package com.sparta.endpointtesting;

import com.sparta.endpointtesting.pojoconfig.pojos.VerifyUserResponse;
import com.sparta.endpointtesting.utils.Helper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class VerifyUserLoginTest {

    private static Response response;

    private static VerifyUserResponse successfulVerificationResponse;
    private static VerifyUserResponse missingEmailVerificationResponse;
    private static VerifyUserResponse missingPasswordVerificationResponse;
    private static VerifyUserResponse invalidCredentialsVerificationResponse;
    private static VerifyUserResponse invalidActionVerificationResponse;


    @BeforeAll
    static void beforeAll(){
        // Happy path: Correct credentials
        response = Helper.postVerifyLogin("api.tester@sparta.com", "testing");
        successfulVerificationResponse = response.as(VerifyUserResponse.class);

        // Sad path: missing email
        response = Helper.postVerifyLoginSpecifyParams(Map.of(
                "password", "testing"
        ));
        missingEmailVerificationResponse = response.as(VerifyUserResponse.class);

        // Sad path: missing password
        response = Helper.postVerifyLoginSpecifyParams(Map.of(
                "email", "api.tester@sparta.com"
        ));
        missingPasswordVerificationResponse = response.as(VerifyUserResponse.class);

        // Sad path: incorrect credentials
        response = Helper.postVerifyLogin("api.tetser@sparta.com", "twsting");
        invalidCredentialsVerificationResponse = response.as(VerifyUserResponse.class);

        // Sad path: incorrect REST verb
        response = RestAssured
                .given()
                    .spec(Helper.verifyLoginRequestSpecifyFormParams(Map.of(
                        "email", "api.tester@sparta.com",
                        "password", "testing"
                    )))
                .when()
                    .delete()
                .then()
                    .extract().response();
        invalidActionVerificationResponse = response.as(VerifyUserResponse.class);
    }

    @Test
    @DisplayName("Valid email and password return HTTP 200 and success message")
    void givenValidEmailAndPasswordReturnsHTTP200AndSuccessMessage(){
        MatcherAssert.assertThat(successfulVerificationResponse.getResponseCode(), Matchers.is(200));
        MatcherAssert.assertThat(successfulVerificationResponse.getMessage(), Matchers.is("User exists!"));
    }

    @Test
    @DisplayName("Missing email returns HTTP 400")
    void givenMissingEmailReturnsHTTP400(){
        MatcherAssert.assertThat(missingEmailVerificationResponse.getResponseCode(), Matchers.is(400));
    }

    @Test
    @DisplayName("Missing password returns HTTP 400")
    void givenMissingPasswordReturnsHTTP400(){
        MatcherAssert.assertThat(missingPasswordVerificationResponse.getResponseCode(), Matchers.is(400));
    }

    @Test
    @DisplayName("Invalid username and password returns HTTP 404")
    void givenInvalidCredentialsReturnsHTTP404(){
        MatcherAssert.assertThat(invalidCredentialsVerificationResponse.getResponseCode(), Matchers.is(404));
        MatcherAssert.assertThat(invalidCredentialsVerificationResponse.getMessage(), Matchers.is("User not found!"));
    }

    @Test
    @DisplayName("Delete request returns HTTP 405")
    void givenDeleteRequestReturnsHTTP405(){
        MatcherAssert.assertThat(invalidActionVerificationResponse.getResponseCode(), Matchers.is(405));
    }
}
