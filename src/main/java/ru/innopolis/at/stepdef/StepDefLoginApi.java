package ru.innopolis.at.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import ru.innopolis.at.api.models.request.LoginDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepDefLoginApi extends StepDefBaseApi {



    @Given("Пользователь = {string}, Пароль = {string}")
    public LoginDTO prepareSpec(String user, String pass) {
        return loginDTO = LoginDTO.builder().userName(user).password(pass).build();
    }


    @When("Отправляем Post запрос на {string}")
    public void requestLogin(String endpoint) {
        response =
                given(requestSpecification)
                        .body(loginDTO)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract().response();
    }

    @Then("Получаем userId")
    public void getUserId() {
        userId = response.then().extract().jsonPath().get("userId").toString();
    }

    @Then("Получаем token")
    public void getToken() {
        sessionToken = response.then().extract().jsonPath().get("token").toString();
    }

    @Then("Проверяем, что авторизация успешна. Статус код {int}")
    public void responseCode(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }


    @Then("Проверяем, что авторизация не успешна. Статус код {int}, result = {string}")
    public void responseCodeAndResult(Integer statusCode, String result) {
        response.then().assertThat().statusCode(statusCode).assertThat().body("result", equalTo(result));
    }

}
