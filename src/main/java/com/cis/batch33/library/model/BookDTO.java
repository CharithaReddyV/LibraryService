package com.cis.batch33.library.model;

import java.util.List;

public class BookDTO {

    private Integer bookId;
    private String title;
    private String authorName;
    private Integer yearPubished;
    private Integer quantity;
    private List<BookISBNDTO> isbns;

    // Constructors, getters, and setters
    // Make sure you have getter and setter methods for all fields

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getYearPubished() {
        return yearPubished;
    }

    public void setYearPubished(Integer yearPubished) {
        this.yearPubished = yearPubished;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<BookISBNDTO> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<BookISBNDTO> isbns) {
        this.isbns = isbns;
    }
}
