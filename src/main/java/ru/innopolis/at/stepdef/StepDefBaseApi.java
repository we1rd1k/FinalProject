package ru.innopolis.at.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.innopolis.at.api.models.request.LoginDTO;
import ru.innopolis.at.ui.Props;

import static io.restassured.RestAssured.given;
import static ru.innopolis.at.api.Endpoint.*;

public class StepDefBaseApi {

    public static LoginDTO loginDTO;
    public static Response response;
    public static String userId;
    public static String sessionToken;

    private final Props props = ConfigFactory.create(Props.class);

    protected final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri(props.demoqaUrl())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    private String getToken() {
        return given(requestSpecification)
                .body(LoginDTO.builder().build())
                .when()
                .post(GENERATE.getEndPoint())
                .then()
                .log()
                .body()
                .statusCode(200).extract().jsonPath().get("token");
    }


    protected final RequestSpecification requestSpecificationWithAuth = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri(props.demoqaUrl()).addHeader("Authorization", "Bearer " + getToken())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

}
