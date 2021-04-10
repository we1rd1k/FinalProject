package ru.innopolis.at.api;

import io.qameta.allure.restassured.AllureRestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import ru.innopolis.at.api.models.request.LoginDTO;
import ru.innopolis.at.ui.Props;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.innopolis.at.api.Endpoint.*;

public class LoginTest extends BaseApiTest{

    private final Props props = ConfigFactory.create(Props.class);

    @Test
    void loginTest() {
        given(requestSpecification)
                .filter(new AllureRestAssured())
                .body(LoginDTO
                        .builder()
                        .build())
                .when()
                .post(LOGIN.getEndPoint())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void failedAuthTest() {
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

    @Test
    void failedLoginTest() {
        given()
                .spec(requestSpecification)
                .body(LoginDTO.builder()
                        .userName("asf")
                        .password("dsf")
                        .build())
                .when()
                .post(GENERATE.getEndPoint())
                .then().assertThat()
                .body("result", equalTo("User authorization failed."))
                .assertThat().body("status", equalTo("Failed"))
                .statusCode(200);
    }
}
