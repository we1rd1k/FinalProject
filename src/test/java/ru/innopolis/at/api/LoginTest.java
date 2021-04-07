package ru.innopolis.at.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri("https://demoqa.com")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test

    void loginTest() {
        given(requestSpecification)
                .filter(new AllureRestAssured())
                .body(LoginDTO
                        .builder()
                        .build())
                .log()
                .all()
                .when()
                .post("Account/v1/Login")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void failedLoginTest() {
        given()
                .spec(requestSpecification)
                .body(LoginDTO.builder()
                        .userName("asf")
                        .password("dsf")
                        .build())
                .when()
                .post("Account/v1/Authorized")
                .then()
                .assertThat()
                .body("message", equalTo("User not found!"))
                .statusCode(404);
    }
}
