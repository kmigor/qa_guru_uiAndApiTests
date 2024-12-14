package tests;

import api.AccountApi;
import api.BookStoreApi;
import helpers.extensions.WithLogin;
import models.GetListOfBooksResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoQaBookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления товара из списка")
    void successfulDeleteBookTest() {
        BookStoreApi bookStoreApi = new BookStoreApi();
        AccountApi accountApi = new AccountApi();
        ProfilePage profilePage = new ProfilePage();

        bookStoreApi.deleteAllBooksInCart();
        profilePage.setIsbn(bookStoreApi.addBookToList("9781449337711"));
        profilePage.openPageWithAuthorization();
        profilePage.checkThatBookIsAdded();
        profilePage.deleteCertainBook();
        profilePage.checkThatTheBookDeleted();

        step("Получить список книг в корзине, и проверить, что книга удалена, через API", () -> {
            GetListOfBooksResponseModel response = accountApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }
}