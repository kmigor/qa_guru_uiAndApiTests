package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookModel {

    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    String description;
    String website;
    int pages;
}
