package api;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public class CommonApi {
    private static final String URI = "https://qa-scooter.praktikum-services.ru/";

    private final Header jsonHeader = new Header("Content-type", "application/json");

    protected RequestSpecification request() {
        return RestAssured.given()
                .baseUri(URI)
                .header(jsonHeader);
    }
}