package ru.innopolis.at.api;

import org.junit.jupiter.api.Test;
import ru.innopolis.at.api.models.request.BooksDTO;
import ru.innopolis.at.api.models.request.LoginDTO;
import ru.innopolis.at.api.models.response.ProfileBooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.innopolis.at.api.Endpoint.*;

public class DeleteBookTest extends BaseApiTest {

    @Test
    void deleteBookTest() {
        List<BooksDTO.CollectionDTO> isbn = new ArrayList<>();
        Collections.addAll(isbn, BooksDTO.CollectionDTO.builder().isbnItems("9781449337711").build());
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

        given(requestSpecificationWithAuth)
                .queryParam("UserId", userId)
                .delete(BOOKS.getEndPoint())
                .then()
                .statusCode(204);

        given(requestSpecificationWithAuth)
                .body(BooksDTO.builder().userId(userId).collectionOfIsbns(isbn).build())
                .when()
                .post(BOOKS.getEndPoint())
                .then()
                .log()
                .body().statusCode(201);

        ProfileBooks profileBooks =
                given(requestSpecificationWithAuth)
                        .get(USER.getEndPoint() + userId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ProfileBooks.class);

        String bookForDeletion = profileBooks.getBooks().stream().findFirst().get().getIsbn();

        given(requestSpecificationWithAuth)
                .body(BooksDTO.builder().userId(userId).isbn(bookForDeletion).build())
                .when()
                .post(BOOKS.getEndPoint())
                .then()
                .assertThat().body("isbn", equalTo(bookForDeletion))
                .statusCode(204);

    }
}
