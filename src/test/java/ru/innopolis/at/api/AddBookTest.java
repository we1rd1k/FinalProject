package ru.innopolis.at.api;

import org.junit.jupiter.api.Test;
import ru.innopolis.at.api.models.request.BooksDTO;
import ru.innopolis.at.api.models.request.LoginDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.innopolis.at.api.Endpoint.*;

public class AddBookTest extends BaseApiTest {

    @Test
    void addBookTest() {
        List<BooksDTO.CollectionDTO> isbn = new ArrayList<>();
        Collections.addAll(isbn, BooksDTO.CollectionDTO.builder().isbnItems("9781449337711").build());
        String token = given(requestSpecification)
                .body(LoginDTO.builder().build())
                .when()
                .post(GENERATE.getEndPoint())
                .then()
                .log()
                .body()
                .statusCode(200).extract().jsonPath().get("token");

        String userId =
                given(requestSpecification)
                        .body(LoginDTO.builder().build())
                        .when()
                        .post(LOGIN.getEndPoint())
                        .then()
                        .log()
                        .body()
                        .statusCode(200)
                        .extract().jsonPath().get("userId").toString();

        given(requestSpecification).header("Authorization", "Bearer " + token)
                .body(BooksDTO.builder().userId(userId).collectionOfIsbns(isbn).build())
                .when()
                .post(BOOKS.getEndPoint())
                .then()
                .log()
                .body().statusCode(201);
    }
}
