package com.cis.batch33.library.model;

import lombok.Data;
import java.util.Date;

@Data
public class CheckoutDTO {
    private Integer id;
    private Long bookIsbn; // Change isbn type to Long
    private Integer memberId; // Add memberId field
    private Date checkoutDate;
    private Date dueDate;
    private boolean returned;

    public Long getIsbn() {
        return bookIsbn;
    }

    public void setIsbn(Long isbn) {
        this.bookIsbn = isbn;
    }

    public  int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }



}