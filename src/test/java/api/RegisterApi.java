package api;

import models.CreateUserModel;
import models.CreateUserResponseModel;
import models.GetUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.CreateUserSpec.*;

public class RegisterApi {
    public CreateUserResponseModel postRequest(CreateUserModel request) {
        CreateUserResponseModel response = step("Успешное создание пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/register")
                        .then()
                        .spec(successfulUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));
        return response;
    }

    public void checkEmailAndPassword(CreateUserResponseModel response) {
        step("Проверка создания пользователя по id, токену", () -> {
            assertEquals(4, response.getId());
            assertNotNull(response.getToken(), "Токен должен быть не null");
            assertEquals(17, response.getToken().length(), "Длина токена должна быть 17 символов");
        });
    }

    public CreateUserResponseModel negativeEmailPostReques(CreateUserModel request) {
        CreateUserResponseModel response = step("Неуспешное создание пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/register")
                        .then()
                        .spec(createUserResponseSpec400)
                        .extract().as(CreateUserResponseModel.class));
        return response;
    }

    public void checkNegativeEmail(CreateUserResponseModel response) {
        step("Проверка ошибки при создании пользователя", () -> {
            assertEquals("Missing password", response.getError());
        });
    }

    public GetUserResponseModel getIdUser() {
        GetUserResponseModel response = step("Получение Id пользователя", () ->
                given(createRequestSpec)
                        .when()
                        .get("/users/4")
                        .then()
                        .spec(successfulUserResponseSpec)
                        .extract().as(GetUserResponseModel.class));
        return response;
    }

    public void checkIdUser(GetUserResponseModel response) {
        step("Проверка id пользователя", () -> {
            assertEquals(String.valueOf(4), response.getData().getId(), "ID пользователя должен совпадать");
        });
    }
}

