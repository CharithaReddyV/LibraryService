package com.cis.batch33.library.model;

import java.util.List;

public class BookISBNDTO {

    private Long isbn;
    private Integer bookId;

    private List<CheckoutDTO> checkouts;

    // Constructors, getters, and setters

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
}
