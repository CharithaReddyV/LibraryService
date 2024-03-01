package com.cis.batch33.library.model;

import lombok.Data;
import java.util.List;

@Data
public class Member {
    private Integer memberId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long phoneNumber;
    private String membershipLevel;
    private AddressDTO address;
    private List<CheckoutDTO> checkouts;
}
