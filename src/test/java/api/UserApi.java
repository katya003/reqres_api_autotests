package api;

import models.CreateUserNameJobModel;
import models.CreateUserNameJobResponseModel;
import models.UpdateSuccessfulResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.UserSpec.createRequestSpec;
import static specs.UserSpec.defaultLoggingSpec;

public class UserApi {

    public CreateUserNameJobResponseModel postNameJobRequest(CreateUserNameJobModel request) {
        CreateUserNameJobResponseModel response = step("Создание пользователя с именем и работой", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post("/user")
                        .then()
                        .statusCode(201))
                .extract().as(CreateUserNameJobResponseModel.class);
        return response;
    }

    public UpdateSuccessfulResponseModel patchJobRequest(CreateUserNameJobModel request) {
        UpdateSuccessfulResponseModel response = step("Изменение работы у пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(defaultLoggingSpec)
                        .extract().as(UpdateSuccessfulResponseModel.class));
        return response;
    }

    public UpdateSuccessfulResponseModel patchNameRequest(CreateUserNameJobModel request) {
        UpdateSuccessfulResponseModel response = step("Изменение имени у пользователя", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(defaultLoggingSpec)
                        .extract().as(UpdateSuccessfulResponseModel.class));
        return response;
    }

    public void deleteData() {
        step("Удалить данные", () -> {
            given(createRequestSpec)
                    .delete("/users/2")
                    .then()
                    .statusCode(204);
        });
    }
}



