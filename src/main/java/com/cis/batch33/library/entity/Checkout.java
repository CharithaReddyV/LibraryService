package com.cis.batch33.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookISBN bookIsbn; // Corrected association to BookISBN

    @ManyToOne
    @JoinColumn(name = "member_id")
    private LibraryMember libraryMember;

    private Date checkoutDate;
    private Date dueDate;
    private boolean isReturned;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookISBN getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(BookISBN bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
