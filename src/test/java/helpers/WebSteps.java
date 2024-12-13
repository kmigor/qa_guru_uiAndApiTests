package helpers;

import api.BookStoreApi;
import io.qameta.allure.Step;
import pages.ProfilePage;

public class WebSteps {
    BookStoreApi bookStoreApi = new BookStoreApi();
    ProfilePage profilePage = new ProfilePage();

    @Step("Очистить корзину с книгами")
    public void deleteBooks() {
        bookStoreApi.deleteAllBooksInCart();
    }

    @Step("Добавить определенную книгу в корзину")
    public void addBook(String isbn) {
        bookStoreApi.addBookToList(isbn);
        profilePage.setIsbn(isbn);
    }

    @Step("Открыть страницу под учетной записью")
    public void openPageWithLogin() {
        profilePage.openPage()
                .checkThatLoginSucceeded();
    }

    @Step("Проверить наличие книги")
    public void checkThatBookIsAdded() {
        profilePage.checkThatBookIsAdded();
    }

    @Step("Удалить книгу")
    public void deleteBook() {
        profilePage.deleteCertainBook();
    }

    @Step("Проверить, что книга удалена, через UI")
    public void checkThatBookIsDeleted() {
        profilePage.checkThatTheBookDeletedUI();
    }
}