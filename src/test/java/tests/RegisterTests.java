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

@Owner("Ekaterina")
@Feature("API Registration")
@Tag("API")

public class RegisterTests extends TestBase {
    RegisterApi registerApi = new RegisterApi();
    CreateUserModel request = new CreateUserModel();

    @Test
    @DisplayName("Проверка регистрации с заполнением email и пароля")
    @Story("Позитивный тест")
    public void successfulRegTest() {
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");
        CreateUserResponseModel response = registerApi.postRequest(request);
        registerApi.checkEmailAndPassword(response);
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным email")
    @Story("Негативный тест")
    public void unSuccessfulRegTest() {
        request.setEmail("sydney@fife");
        CreateUserResponseModel response = registerApi.negativeEmailPostReques(request);
        registerApi.checkNegativeEmail(response);
    }

    @Test
    @DisplayName("Проверка получения id пользователя")
    @Story("Позитивный тест")
    void getUserByIdTest() {
        GetUserResponseModel response = registerApi.getIdUser();
        registerApi.checkIdUser(response);
    }
}
