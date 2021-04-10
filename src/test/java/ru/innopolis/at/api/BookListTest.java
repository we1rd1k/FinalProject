package ru.innopolis.at.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.innopolis.at.api.Endpoint.BOOKS;

public class BookListTest extends BaseApiTest{


    @Test
    void bookListTest() {
        String responseBody = given()
                .spec(requestSpecification)
                .when()
                .get(BOOKS.getEndPoint())
                .then()
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("bookSchema.json"))
                .statusCode(SC_OK)
                .extract().asString();
        assertNotNull(responseBody);
    }
}
