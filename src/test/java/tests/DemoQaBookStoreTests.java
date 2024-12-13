package tests;

import api.AccountApi;
import helpers.WebSteps;
import helpers.extensions.WithLogin;
import models.GetListOfBooksResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoQaBookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления товара из списка")
    void successfulDeleteBookTest() {
        WebSteps steps = new WebSteps();

        steps.deleteBooks();
        steps.addBook("9781449337711");
        steps.openPageWithLogin();
        steps.checkThatBookIsAdded();
        steps.deleteBook();
        steps.checkThatBookIsDeleted();

        step("Получить список книг в корзине, и проверить, что книга удалена, через API", () -> {
            AccountApi accountApi = new AccountApi();
            GetListOfBooksResponseModel response = accountApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }
}