package com.cis.batch33.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "library_member")
@Data
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "membership_level")
    private String membershipLevel;

    @Transient // This field will not be persisted to the database
    private Integer addressId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "libraryMember", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Checkout> checkouts;

    // Getter and setter for addressId (optional)
    public Integer getAddressId() {
        return (address != null) ? address.getAddressId() : null;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getId() {
        return memberId;
    }

    // Setter for memberId
    public void setId(Integer memberId) {
        this.memberId = memberId;
    }
}
