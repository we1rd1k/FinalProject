package ru.innopolis.at.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddDeleteBookTest {

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri("https://demoqa.com")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void addBookTest() {
        List isbn = new ArrayList<>();
        Collections.addAll(isbn, BooksDTO.CollectionDTO.builder().isbn("9781449331818").build());
        given(requestSpecification)
                .body(LoginDTO.builder().build())
                .when()
                .post(Endpoints.GENERATE.getEndPoint())
                .then()
                .log()
                .body()
                .statusCode(200);

        String userId =
                given(requestSpecification)
                        .body(LoginDTO.builder().build())
                        .when()
                        .post(Endpoints.LOGIN.getEndPoint())
                        .then()
                        .log()
                        .body()
                        .statusCode(200)
                        .extract().jsonPath().get("userId").toString();

        given(requestSpecification)
                .body(LoginDTO.builder().build())
                .when()
                .post(Endpoints.AUTHORIZED.getEndPoint())
                .then().log()
                .body()
                .statusCode(200);

        given(requestSpecification)
                .body(BooksDTO.builder().userId(userId).collectionOfIsbns(isbn).build())
                .when()
                .post(Endpoints.BOOKS.getEndPoint())
                .then()
                .log()
                .body().statusCode(201);
    }
}
