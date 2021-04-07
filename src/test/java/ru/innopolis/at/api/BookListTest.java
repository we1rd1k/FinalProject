package ru.innopolis.at.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookListTest {

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://demoqa.com")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void BookListTest() {
        String responseBody = given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("bookSchema.json"))
                .statusCode(SC_OK)
                .extract().asString();
        assertNotNull(responseBody);
    }
}
