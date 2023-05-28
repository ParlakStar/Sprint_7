package test;

import base.BaseCourier;
import constants.CourierFields;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

@Feature("Создание курьера")
public class CreateCourierTest extends BaseCourier {
    @Test
    @DisplayName("Отправка запроса без поля login")
    @Description("Невозможно создать курьера без логина")
    public void createCourierLoginNullValueTest() {
        generateCustomCourierData(CourierFields.PASSWORD, CourierFields.FIRST_NAME);
        Response response = courierApi.createCourier(courierCard);
        response.then().assertThat().body("message",
                        Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .and().statusCode(SC_BAD_REQUEST);
    }

    /**
     * Тест создания курьера без поля firstName.
     * Проверяет, что возможно создать курьера без поля firstName.
     */
    @Test
    @DisplayName("Отправка запроса без поля firstName")
    @Description("Возможно создать курьера без поля firstName")
    public void createCourierNameNullValueTest() {
        generateCustomCourierData(CourierFields.LOGIN, CourierFields.PASSWORD);
        Response response = courierApi.createCourier(courierCard);
        response.then()
                .assertThat()
                .body("ok", Matchers.equalTo(true))
                .statusCode(SC_CREATED);
    }

    /**
     * Тест создания курьера с пустым полем login.
     * Проверяет, что невозможно создать курьера с пустым логином.
     */
    @Test
    @DisplayName("Отправка запроса с пустым полем login")
    @Description("Невозможно создать курьера с пустым логином")
    public void createCourierLoginEmptyValueTest() {
        generateCourierData();
        courierCard.setLogin("");
        Response response = courierApi.createCourier(courierCard);
        response.then()
                .assertThat()
                .body("message", Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .statusCode(SC_BAD_REQUEST);
    }

    /**
     * Тест создания курьера с пустым полем password.
     * Проверяет, что невозможно создать курьера с пустым паролем.
     */
    @Test
    @DisplayName("Отправка запроса с пустым полем password")
    @Description("Невозможно создать курьера с пустым паролем")
    public void createCourierPassEmptyValueTest() {
        generateCourierData();
        courierCard.setPassword("");
        Response response = courierApi.createCourier(courierCard);
        response.then()
                .assertThat()
                .body("message", Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .statusCode(SC_BAD_REQUEST);
    }

    /**
     * Тест создания корректного курьера.
     * Проверяет успешное создание курьера.
     */
    @Test
    @DisplayName("Отправка корректного запроса /api/v1/courier")
    @Description("Удачное создание для /api/v1/courier")
    public void createCourierHappyPathTest() {
        generateCourierData();
        Response response = courierApi.createCourier(courierCard);
        response.then()
                .assertThat()
                .body("ok", Matchers.equalTo(true))
                .statusCode(SC_CREATED);
    }

    /**
     * Тест создания курьера дважды.
     * Проверяет, что невозможно создать одного и того же курьера дважды.
     */
    @Test
    @DisplayName("Отправка запроса /api/v1/courier дважды")
    @Description("Невозможно создать одного и того же курьера дважды")
    public void createCourierTwiceTest() {
        generateCourierData();
        courierApi.createCourier(courierCard);
        Response response = courierApi.createCourier(courierCard);
        response.then()
                .assertThat()
                .body("message", Matchers.equalTo(ErrorMessage.EXIST_LOGIN))
                .statusCode(SC_CONFLICT);
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Проверяет наличие тестового курьера и при необходимости удаляет его.
     */
    @After
    public void afterDelete() {
        if (courierApi.courierId(courierCard) == null) {
            System.out.println("Удаление не требуется");
        } else {
            deleteTestCourier();
            System.out.println("Удаление прошло успешно");
        }
    }
}




