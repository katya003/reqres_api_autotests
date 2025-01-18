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

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("Ekaterina")
@Feature("API User")
@Tag("API")

public class UserTests extends TestBase {
    UserApi userApi = new UserApi();
    CreateUserNameJobModel request = new CreateUserNameJobModel();

    @Test
    @DisplayName("Создание пользователя")
    @Story("Тестирование создания пользователя с именем и работой")
    public void createUserNameJobTest() {
        request.setName("morpheus");
        request.setJob("leader");
        CreateUserNameJobResponseModel response = userApi.postNameJobRequest(request);
        //userApi.checkUserNameAndJob(response);

        step("Проверка создания пользователя с именем и работой", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @Test
    @DisplayName("Редактирование работы пользователя")
    @Story("Тестирование изменения работы у пользователя")
    public void updateSuccessfulJobTest() {
        request.setJob("zion resident");
        UpdateSuccessfulResponseModel response = userApi.patchJobRequest(request);

        step("Проверка изменения работы у пользователя", () -> {
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    @DisplayName("Редактирование имени пользователя")
    @Story("Тестирование изменения имени у пользователя")
    void updateSuccessfulNameTest() {
        request.setName("morpheus");
        UpdateSuccessfulResponseModel response = userApi.patchNameRequest(request);

        step("Проверка изменения имени у пользователя", () -> {
            assertEquals("morpheus", response.getName());
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    @Story("Тестирование удаления пользователя")
    void deleteUserTest() {
        userApi.deleteData();
    }
}

