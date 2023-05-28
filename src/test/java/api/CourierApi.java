package api;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CourierCard;
import pojo.CourierId;

import static io.restassured.RestAssured.given;

public class CourierApi extends CommonApi {

    public Response createCourier(CourierCard courierCard) {
        return given(request())
                .body(courierCard)
                .when()
                .post(PathApi.COURIER_BASE_URL);
    }

    public Response requestLogin(CourierCard courierCard) {
        return given(request())
                .body(courierCard)
                .when()
                .post(PathApi.COURIER_LOGIN);
    }

    @Step("Удаление курьера")
    public void deleteCourier(CourierCard courierCard) {
        String courierId = courierId(courierCard);
        deleteCourierById(courierId);
    }

    @Step("Поиск курьера по ID")
    public String courierId(CourierCard courierCard) {
        Response response = requestLogin(courierCard);
        CourierId courierId = response.as(CourierId.class);

        return courierId.getId();
    }

    @Step("Удалить курьера по ID")
    public void deleteCourierById(String courierId) {
         given(request())
                .delete(PathApi.COURIER_BASE_URL + "/{id}", courierId);
    }
}
