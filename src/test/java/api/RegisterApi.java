package api;

import models.CreateUserModel;
import models.CreateUserResponseModel;
import models.GetUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.UserSpec.createRequestSpec;
import static specs.UserSpec.defaultLoggingSpec;

public class RegisterApi {
    public CreateUserResponseModel postRequest(CreateUserModel request) {
        CreateUserResponseModel response = step("Успешное создание пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/register")
                        .then()
                        .spec(defaultLoggingSpec)
                        .extract().as(CreateUserResponseModel.class));
        return response;
    }

    public CreateUserResponseModel negativeEmailPostReques(CreateUserModel request) {
        CreateUserResponseModel response = step("Неуспешное создание пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/register")
                        .then()
                        .statusCode(400))
                .extract().as(CreateUserResponseModel.class);
        return response;
    }

    public GetUserResponseModel getIdUser() {
        GetUserResponseModel response = step("Получение Id пользователя", () ->
                given(createRequestSpec)
                        .when()
                        .get("/users/4")
                        .then()
                        .spec(defaultLoggingSpec)
                        .extract().as(GetUserResponseModel.class));
        return response;
    }
}

