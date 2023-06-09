package api;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.OrderCard;

import static io.restassured.RestAssured.given;

public class OrderApi extends CommonApi {

    @Step("Создание заказа, POST /api/v1/orders")
    public Response postRequestCreateOrder(OrderCard orderCard) {
        return given(request())
                .body(orderCard)
                .when()
                .post(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение полного списка заказов, GET /api/v1/orders")
    public Response getRequestGetOrderList() {
        return given(request())
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по одному параметру, GET /api/v1/orders")
    public Response getRequestGetOrderList(String queryParam, String queryParamValue) {
        return given(request())
                .queryParam(queryParam, queryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по двум параметрам, GET /api/v1/orders")
    public Response getRequestGetOrderList(String firstQueryParam, String firstQueryParamValue,
                                           String secondQueryParam, String secondQueryParamValue) {
        return given(request())
                .queryParam(firstQueryParam, firstQueryParamValue)
                .queryParam(secondQueryParam, secondQueryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по трем параметрам, GET /api/v1/orders")
    public Response getRequestGetOrderList(String firstQueryParam, String firstQueryParamValue,
                                           String secondQueryParam, String secondQueryParamValue,
                                           String thirdQueryParam, String thirdQueryParamValue) {
        return given(request())
                .queryParam(firstQueryParam, firstQueryParamValue)
                .queryParam(secondQueryParam, secondQueryParamValue)
                .queryParam(thirdQueryParam, thirdQueryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Отмена заказа по трэк номеру, PUT /api/v1/orders/cancel")
    public void putRequestCancelOrderByTrack(int trackNumber) {
        given(request())
                .queryParam("track", trackNumber)
                .put(PathApi.CANCEL_ORDER);
    }
}
