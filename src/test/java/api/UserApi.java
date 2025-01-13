package api;

import models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateUserSpec.*;

public class UserApi {

    public CreateUserNameJobResponseModel postNameJobRequest(CreateUserNameJobModel request) {
        CreateUserNameJobResponseModel response = step("Создание пользователя с именем и работой", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/user")
                        .then()
                        .spec(createNameJobResponseSpec201)
                        .extract().as(CreateUserNameJobResponseModel.class));
        return response;
    }

    public void checkUserNameAndJob(CreateUserNameJobResponseModel response) {
        step("Проверка создания пользователя с именем и работой", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    public UpdateSuccessfulResponseModel patchJobRequest(CreateUserNameJobModel request) {
        UpdateSuccessfulResponseModel response = step("Изменение работы у пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(successfulUserResponseSpec)
                        .extract().as(UpdateSuccessfulResponseModel.class));
        return response;
    }

    public void updateJob(UpdateSuccessfulResponseModel response) {
        step("Проверка изменения работы у пользователя", () -> {
            assertEquals("zion resident", response.getJob());
        });
    }

    public UpdateSuccessfulResponseModel patchNameRequest(CreateUserNameJobModel request) {
        UpdateSuccessfulResponseModel response = step("Изменение имени у пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(successfulUserResponseSpec)
                        .extract().as(UpdateSuccessfulResponseModel.class));
        return response;
    }

    public void updateName(UpdateSuccessfulResponseModel response) {
        step("Проверка изменения имени у пользователя", () -> {
            assertEquals("morpheus", response.getName());
        });
    }

    public void deleteData() {
        step("Удалить данные", () ->
                given(createRequestSpec)
                        .delete("/users/2")
                        .then()
                        .statusCode(204));
    }
}


