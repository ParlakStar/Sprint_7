package test;

import base.BaseOrder;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.TrackCard;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderNullColorTest extends BaseOrder {

    /**
     * Тест создания заказа с полем color = null
     */
    @Test
    public void createOrderNullColorValue() {
        generateOrderData();
        Response response = orderApi.postRequestCreateOrder(orderCard);
        response.then().assertThat().body("track", notNullValue())
                .and().statusCode(SC_CREATED);
        cancelTestOrder(response.as(TrackCard.class));
    }
}
