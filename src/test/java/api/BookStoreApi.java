package api;

import helpers.extensions.LoginExtension;
import models.AddListOfBooksRequestModel;
import models.IsbnModel;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreWithJsonRequest;
import static specs.DemoQaBookStoreSpecifications.responseLogging;

public class BookStoreApi {

    public void deleteAllBooksInCart() {

        step("Запрос удаления книг из корзины", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + LoginExtension.cookies.getToken())
                    .queryParam("UserId", LoginExtension.cookies.getUserId())

                    .when()
                    .delete("/BookStore/v1/Books")

                    .then()
                    .spec(responseLogging)
                    .statusCode(204);
        });
    }

    public void addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddListOfBooksRequestModel request = new AddListOfBooksRequestModel(LoginExtension.cookies.getUserId(), List.of(isbnModel));

        step("Сделать запрос добавления книги в корзину", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + LoginExtension.cookies.getToken())
                    .body(request)

                    .when()
                    .post("/BookStore/v1/Books")

                    .then()
                    .spec(responseLogging)
                    .statusCode(201);
        });
    }
}