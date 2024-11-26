package models;

import lombok.Data;

@Data
public class BookModel {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publish_date;
    private String publisher;
    private String description;
    private String website;
    private int pages;
}