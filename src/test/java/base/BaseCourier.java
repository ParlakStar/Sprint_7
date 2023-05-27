package base;

import action.CourierApi;
import action.CourierData;
import constants.CourierFields;
import io.qameta.allure.Step;
import resources.CourierCard;

public class BaseCourier {
    private final CourierData courierData = new CourierData();
    public CourierApi courierApi = new CourierApi();
    public CourierCard courierCard;

    @Step("Создание и заполение карточки курьера (login + password + firstname)")
    public void generateCourierData() {
        courierData.generateLoginPassName();
        courierCard = new CourierCard(
                courierData.getCourierLogin(),
                courierData.getCourierPassword(),
                courierData.getCourierFirstName());
    }
    @Step("Создание курьера с рандомными данными")
    public void createNewTestCourier() {
        generateLogPassCourierData();
        courierApi.createCourier(courierCard);
    }
    @Step("Создание и заполение карточки курьера (login + password)")
    private void generateLogPassCourierData() {
        courierData.generateLoginPassName();
        courierCard = new CourierCard(
                courierData.getCourierLogin(),
                courierData.getCourierPassword());
    }

    @Step("Создание и заполнение карточки курьера случайными данными (при помощи полей)")
    public void generateCustomCourierData(String firstField, String secondField) {
        courierData.generateLoginPassName();
        courierCard = new CourierCard();
        fillField(firstField);
        fillField(secondField);
    }

    private void fillField(String field) {
        switch (field) {
            case CourierFields.LOGIN:
                courierCard.setLogin(courierData.getCourierLogin());
                break;
            case CourierFields.PASSWORD:
                courierCard.setPassword(courierData.getCourierPassword());
                break;
            case CourierFields.FIRST_NAME:
                courierCard.setFirstName(courierData.getCourierFirstName());
                break;
        }
    }

    @Step("Удаление созданных тестовых данных")
    public void deleteTestCourier() {
        courierApi.deleteCourier(courierCard);
    }
}
