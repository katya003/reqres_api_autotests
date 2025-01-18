package tests;

import api.RegisterApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import models.CreateUserModel;
import models.CreateUserResponseModel;
import models.GetUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Owner("Ekaterina")
@Feature("API Registration")
@Tag("API")

public class RegisterTests extends TestBase {
    RegisterApi registerApi = new RegisterApi();
    CreateUserModel request = new CreateUserModel();

    @Test
    @DisplayName("Проверка регистрации с заполнением email и пароля")
    @Story("Тестирование регистрации с валидным email и паролем")
    public void successfulRegTest() {
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");
        CreateUserResponseModel response = registerApi.postRequest(request);

        step("Проверка создания пользователя по id, токену", () -> {
            assertEquals(4, response.getId());
            assertNotNull(response.getToken(), "Токен должен быть не null");
            assertFalse(response.getToken().isEmpty(), "Токен не должен быть пустой строкой");
            assertEquals(17, response.getToken().length(), "Длина токена должна быть 17 символов");
        });
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным email")
    @Story("Тестирование регистрации с невалидным email")
    public void unSuccessfulRegTest() {
        request.setEmail("sydney@fife");
        CreateUserResponseModel response = registerApi.negativeEmailPostReques(request);

        step("Проверка ошибки при создании пользователя", () -> {
            assertEquals("Missing password", response.getError());
        });
    }

    @Test
    @DisplayName("Проверка получения id пользователя")
    @Story("Тестирование валидности id пользователя")
    void getUserByIdTest() {
        GetUserResponseModel response = registerApi.getIdUser();

        step("Проверка id пользователя", () -> {
            assertEquals(String.valueOf(4), response.getData().getId(), "ID пользователя должен совпадать");
        });
    }
}
