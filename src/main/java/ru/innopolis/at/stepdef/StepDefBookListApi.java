package ru.innopolis.at.stepdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefBookListApi extends StepDefBaseApi {

    public static Response responseBody;


    @When("Получаем список книг отправляя Get запрос на {string}")
    public void requestBookList(String endpoint) {
        responseBody = given()
                .spec(requestSpecification)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @Then("Проверяем что список книг соответствует json схеме, список не пустой")
    public void validateBooksList() {
        responseBody
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("bookSchema.json"))
                .statusCode(SC_OK);
        assertNotNull(responseBody);
    }
}
