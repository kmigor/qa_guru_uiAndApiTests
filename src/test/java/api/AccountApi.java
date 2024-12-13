package api;

import helpers.extensions.LoginExtension;
import models.GetListOfBooksResponseModel;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.*;

public class AccountApi {

    public LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginRequestModel request = new LoginRequestModel(System.getProperty("bookStoreLogin", "MaxKon"),
                System.getProperty("bookStorePassword", "Qwerty123!"));

        response = step("Сделать запрос логина, и записать ответ", () ->
                given(demoQaBookStoreWithJsonRequest)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(responseLogging)
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        return response;
    }

    public GetListOfBooksResponseModel getListOfBooks() {
        GetListOfBooksResponseModel response;
        response = step("Сделать запрос списка книг в корзине, и записать его в переменную", () ->
                given(demoQaBookStoreCommonRequest)
                        .header("Authorization", "Bearer " + LoginExtension.cookies.getToken())
                        .queryParam("UserId", LoginExtension.cookies.getUserId())

                        .when()
                        .get("/Account/v1/User/" + LoginExtension.cookies.getUserId())

                        .then()
                        .spec(responseLogging)
                        .statusCode(200)
                        .extract().as(GetListOfBooksResponseModel.class));

        return response;
    }
}