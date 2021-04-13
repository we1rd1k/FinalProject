package ru.innopolis.at.stepdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import ru.innopolis.at.api.models.request.BooksDTO;
import ru.innopolis.at.api.models.response.ProfileBooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.innopolis.at.api.Endpoint.BOOKS;
import static ru.innopolis.at.api.Endpoint.USER;

public class StepDefActionsBookApi extends StepDefBaseApi {

    public static Response addBookToCollectionResponse;
    public static ProfileBooks profileBooks;
    public static String bookForDeletion;
    public static Response deleteBookResponse;

    @Then("Очищаем коллекцию книг")
    public void clearProfileList() {
        given(requestSpecificationWithAuth)
                .queryParam("UserId", userId)
                .delete(BOOKS.getEndPoint())
                .then()
                .statusCode(204);
    }

    @When("Добавляем книгу в коллекцию с isbn = {string}")
    public void addBookToCollection(String isbn) {
        List<BooksDTO.CollectionDTO> isbnList = new ArrayList<>();
        Collections.addAll(isbnList, BooksDTO.CollectionDTO.builder().isbnItems(isbn).build());
        addBookToCollectionResponse = given(requestSpecificationWithAuth)
                .body(BooksDTO.builder().userId(userId).collectionOfIsbns(isbnList).build())
                .when()
                .post(BOOKS.getEndPoint())
                .then()
                .extract().response();
    }

    @Then("Проверяем что книга с isbn = {string} успешно добавлена в коллекцию")
    public void checkBookAdded(String isbn) {
        addBookToCollectionResponse
                .then()
                .assertThat().statusCode(201).assertThat().body("books.isbn[0]", equalTo(isbn));
    }

    @When("Получаем данные по книгам в Profile")
    public void getBooksData() {
        profileBooks =
                given(requestSpecificationWithAuth)
                        .get(USER.getEndPoint() + userId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ProfileBooks.class);
    }

    @Then("Получаем isbn книги для удаления")
    public void getIsbnForDeletion() {
        bookForDeletion = profileBooks.getBooks().stream().findFirst().get().getIsbn();
    }

    @When("Отправляем Post запрос на {string} для удаления книги")
    public void deleteBookRequest(String endPoint) {
        deleteBookResponse = given(requestSpecificationWithAuth)
                .body(BooksDTO.builder().userId(userId).isbn(bookForDeletion).build())
                .when()
                .post(endPoint)
                .then()
                .extract()
                .response();
    }

    @Then("Проверяем, что книга успешно удалена")
    public void checkBookDeleted() {
        deleteBookResponse.then().assertThat().body("isbn", equalTo(bookForDeletion))
                .statusCode(204);
    }
}
