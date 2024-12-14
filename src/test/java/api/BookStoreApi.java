package api;

import helpers.extensions.LoginExtension;
import io.qameta.allure.Step;
import models.AddListOfBooksRequestModel;
import models.IsbnModel;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreWithJsonRequest;
import static specs.DemoQaBookStoreSpecifications.responseLogging;

public class BookStoreApi {

    @Step("Очистить корзину с книгами")
    public void deleteAllBooksInCart() {
        given(demoQaBookStoreWithJsonRequest)
                .header("Authorization", "Bearer " + LoginExtension.cookies.getToken())
                .queryParam("UserId", LoginExtension.cookies.getUserId())

                .when()
                .delete("/BookStore/v1/Books")

                .then()
                .spec(responseLogging)
                .statusCode(204);
    }

    @Step("Добавить определенную книгу в корзину")
    public String addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddListOfBooksRequestModel request = new AddListOfBooksRequestModel(LoginExtension.cookies.getUserId(), List.of(isbnModel));
        given(demoQaBookStoreWithJsonRequest)
                .header("Authorization", "Bearer " + LoginExtension.cookies.getToken())
                .body(request)

                .when()
                .post("/BookStore/v1/Books")

                .then()
                .spec(responseLogging)
                .statusCode(201);
        return isbn;
    }
}