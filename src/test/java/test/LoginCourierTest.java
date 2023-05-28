package test;

import base.BaseCourier;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import constants.CourierFields;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Тесты для операции входа курьера
 */
@Feature("Логин курьера")
@JsonIncludeProperties({"id"})
public class LoginCourierTest extends BaseCourier {

    /**
     * Тест успешного входа курьера
     */
    @Test
    @DisplayName("Отправка корректного запроса")
    @Description("Успешный вход курьера")
    public void logInCourierHappyPathTest() {
        createNewTestCourier();
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("id", notNullValue()).statusCode(SC_OK);
    }

    /**
     * Тест отправки запроса с полем login = null
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier/login с полем login = null")
    @Description("Невозможно войти без логина")
    public void logInCourierLoginNullValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getLogin();
        courierCard.setLogin(CourierFields.NULL_VALUE);
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedLogin(initialValue);
    }

    /**
     * Тест отправки запроса с пустым полем login
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier/login с пустым полем login")
    @Description("Невозможно войти без логина")
    public void logInCourierLoginEmptyValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getLogin();
        courierCard.setLogin(CourierFields.EMPTY_VALUE);
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedLogin(initialValue);
    }

    /**
     * Тест отправки запроса с полем password = null
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier/login с полем password = null")
    @Description("Невозможно войти без пароля")
    public void logInCourierPassNullValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.NULL_VALUE);
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body(equalTo(ErrorMessage.SERVICE_UNAVAILABLE))
                .and().statusCode(SC_GATEWAY_TIMEOUT);
        removeTestDataWithChangedPass(initialValue);
    }

    /**
     * Тест отправки запроса с пустым полем password
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier/login с пустым полем password")
    @Description("Невозможно войти без пароля")
    public void logInCourierPassEmptyValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.EMPTY_VALUE);
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedPass(initialValue);
    }

    /**
     * Тест отправки запроса с измененным паролем
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier/login с измененным паролем")
    @Description("Невозможно войти с измененным паролем")
    public void logInCourierChangedPasswordTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.RANDOM_PASSWORD);
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_FOUND_DATA_FOR_LOG_IN))
                .and().statusCode(SC_NOT_FOUND);
        removeTestDataWithChangedPass(initialValue);
    }

    /**
     * Тест отправки запроса с некорректными/несозданными данными курьера
     */
    @Test
    @DisplayName("Отправка запроса с некорректными/не созданными данными курьера")
    @Description("Невозможно войти с некорректными/не созданными данными курьера")
    public void logInCourierIncorrectDataTest() {
        generateCourierData();
        Response response = courierApi.requestLogin(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_FOUND_DATA_FOR_LOG_IN))
                .and().statusCode(SC_NOT_FOUND);
    }

    /**
     * Удаление созданных тестовых данных после изменения логина
     */
    @Step("Удаление созданных тестовых данных после изменения логина")
    public void removeTestDataWithChangedLogin(String initialValue) {
        courierCard.setLogin(initialValue);
        deleteTestCourier();
    }

    /**
     * Удаление созданных тестовых данных после изменения пароля
     */
    @Step("Удаление созданных тестовых данных после изменения пароля")
    public void removeTestDataWithChangedPass(String initialValue) {
        courierCard.setPassword(initialValue);
        deleteTestCourier();
    }

    /**
     * Удаление созданных тестовых данных после каждого теста
     */
    @After
    public void afterDelete() {
        String id = courierApi.courierId(courierCard);
        if (id == null) {
            System.out.println("Удаление не требуется");
        } else {
            deleteTestCourier();
            System.out.println("Удаление прошло успешно");
        }
    }
}

