package action;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.CourierCard;
import resources.CourierId;

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
    public Response deleteCourier(CourierCard courierCard) {
        String courierId = courierId(courierCard);
        return deleteCourierById(courierId);
    }

    @Step("Поиск курьера по ID")
    public String courierId(CourierCard courierCard) {
        Response response = requestLogin(courierCard);
        CourierId courierId = response.as(CourierId.class);
        return courierId.getId();
    }

    @Step("Удалить курьера по ID")
    public Response deleteCourierById(String courierId) {
        return given(request())
                .delete(PathApi.COURIER_BASE_URL + "/{id}", courierId);
    }
}
