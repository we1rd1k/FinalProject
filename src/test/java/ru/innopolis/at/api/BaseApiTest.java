package ru.innopolis.at.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.innopolis.at.api.models.request.LoginDTO;
import ru.innopolis.at.ui.Props;

import static io.restassured.RestAssured.given;
import static ru.innopolis.at.api.Endpoint.GENERATE;

public class BaseApiTest {

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

//    public RequestSpecification getRequestSpecificationWithAuth() {
//        return new RequestSpecBuilder()
//                .addFilter(new AllureRestAssured())
//                .setBaseUri(props.demoqaUrl()).addHeader("Authorization", "Bearer " + getToken())
//                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL)
//                .build();
//    }


    protected final RequestSpecification requestSpecificationWithAuth = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri(props.demoqaUrl()).addHeader("Authorization", "Bearer " + getToken())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
}
