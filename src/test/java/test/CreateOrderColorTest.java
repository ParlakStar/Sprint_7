package test;

import base.BaseOrder;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import resources.OrderColor;
import resources.TrackCard;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Заполнение цвета в заказе")
@DisplayName("Создание заказа с параметризированными данными для цвета")
@RunWith(Parameterized.class)
public class CreateOrderColorTest extends BaseOrder {

    private final String[] color;


    public CreateOrderColorTest(String[] color) {
        this.color = color;
    }

    /**
     * Параметры для теста, определяющие значения поля color
     *
     * @return Массив объектов с параметрами для каждого запуска теста
     */
    @Parameterized.Parameters(name = "Значение поля color: {0}")
    public static Object[][] getColorValue() {
        Object[][] objects = {
                {OrderColor.NO_COLOR.getValue()},
                {OrderColor.GRAY.getValue()},
                {OrderColor.TWO_COLOR.getValue()},
        };
        return objects;
    }

    /**
     * Установка предусловий перед каждым запуском теста
     */
    @Before
    public void setUp() {
        generateOrderData(color);
    }

    /**
     * Тест создания заказа с разными значениями поля color
     */
    @Test
    public void createOrderDiffColorValue() {
        Response response = orderAction.postRequestCreateOrder(orderCard);
        response.then().assertThat().body("track", notNullValue())
                .and().statusCode(SC_CREATED);
        cancelTestOrder(response.as(TrackCard.class));
    }

}

