package com.cis.batch33.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="book_isbn")
@Data
public class BookISBN {

    @Id
    private Long isbn;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @OneToMany(mappedBy = "bookIsbn")
    private List<Checkout> checkouts;

    // Default constructor
    public BookISBN() {
    }

    // Constructor with isbn parameter
    public BookISBN(Long isbn) {
        this.isbn = isbn;
    }


}
