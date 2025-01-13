package tests;

import api.UserApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import models.CreateUserNameJobModel;
import models.CreateUserNameJobResponseModel;
import models.UpdateSuccessfulResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("Ekaterina")
@Feature("API User")
@Tag("API")

public class UserTests extends TestBase {
    UserApi userApi = new UserApi();
    CreateUserNameJobModel request = new CreateUserNameJobModel();

    @Test
    @DisplayName("Создание пользователя")
    @Story("Позитивный тест")
    public void createUserNameJobTest() {
        request.setName("morpheus");
        request.setJob("leader");
        CreateUserNameJobResponseModel response = userApi.postNameJobRequest(request);
        userApi.checkUserNameAndJob(response);
    }

    @Test
    @DisplayName("Редактирование работы пользователя")
    @Story("Позитивный тест")
    public void updateSuccessfulJobTest() {
        request.setJob("zion resident");
        UpdateSuccessfulResponseModel response = userApi.patchJobRequest(request);
        userApi.updateJob(response);
    }

    @Test
    @DisplayName("Редактирование имени пользователя")
    @Story("Позитивный тест")
    void updateSuccessfulNameTest() {
        request.setName("morpheus");
        UpdateSuccessfulResponseModel response = userApi.patchNameRequest(request);
        userApi.updateName(response);
    }

    @Test
    @DisplayName("Удаление пользователя")
    @Story("Позитивный тест")
    void deleteUserTest() {
        userApi.deleteData();
    }
}

