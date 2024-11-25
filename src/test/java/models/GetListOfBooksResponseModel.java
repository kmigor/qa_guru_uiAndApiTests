package models;

import lombok.Data;

import java.util.List;

@Data
public class GetListOfBooksResponseModel {
    String userId;
    String username;
    List<BookModel> books;
}