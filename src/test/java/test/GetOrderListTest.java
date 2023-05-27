package test;

import base.BaseCourier;
import base.BaseOrder;
import constants.OrderListParams;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Получение списка заказов")
public class GetOrderListTest extends BaseOrder {

    private BaseCourier baseCourier;

    @Before
    public void setUp() {
        baseCourier = new BaseCourier();
        baseCourier.createNewTestCourier();
    }

    @Test
    @DisplayName("Получение списка заказов без параметров")
    public void getOrderListNoParamTest() {
        Response response = orderAction.getRequestGetOrderList();
        response.then()
                .assertThat()
                .body("orders", notNullValue())
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Получение списка заказов с параметрами: Limit, Page")
    public void getOrderListParamLimitPageTest() {
        Response response = orderAction.getRequestGetOrderList(
                OrderListParams.LIMIT, orderValue.paramValue(OrderListParams.LIMIT),
                OrderListParams.PAGE, orderValue.paramValue(OrderListParams.PAGE));
        response.then()
                .assertThat()
                .body("orders", notNullValue())
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Получение списка заказов с параметрами: Limit, Page, Nearest Station")
    public void getOrderListParamLimitPageStationTest() {
        Response response = orderAction.getRequestGetOrderList(
                OrderListParams.LIMIT, orderValue.paramValue(OrderListParams.LIMIT),
                OrderListParams.PAGE, orderValue.paramValue(OrderListParams.PAGE),
                OrderListParams.NEAREST_STATION, orderValue.paramValue(OrderListParams.NEAREST_STATION));
        response.then()
                .assertThat()
                .body("orders", notNullValue())
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Получение списка заказов с параметром: Courier ID")
    public void getOrderListParamCourierIdTest() {
        String courierId = baseCourier.courierApi.courierId(baseCourier.courierCard);
        Response response = orderAction.getRequestGetOrderList(OrderListParams.COURIER_ID, courierId);
        response.then()
                .assertThat()
                .body("orders", notNullValue())
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Получение списка заказов с параметрами: Courier ID, nearestStation")
    public void getOrderListParamCourierIdStationTest() {
        String courierId = baseCourier.courierApi.courierId(baseCourier.courierCard);
        Response response = orderAction.getRequestGetOrderList(
                OrderListParams.COURIER_ID, courierId,
                OrderListParams.NEAREST_STATION, orderValue.paramValue(OrderListParams.NEAREST_STATION));
        response.then()
                .assertThat()
                .body("orders", notNullValue())
                .statusCode(SC_OK);
    }

    @After
    public void tearDown() {
        baseCourier.deleteTestCourier();
    }
}

