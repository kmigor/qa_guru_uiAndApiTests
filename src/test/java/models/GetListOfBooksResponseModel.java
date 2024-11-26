package models;

import lombok.Data;

import java.util.List;

@Data
public class GetListOfBooksResponseModel {
    private String userId;
    private String username;
    private List<BookModel> books;
}