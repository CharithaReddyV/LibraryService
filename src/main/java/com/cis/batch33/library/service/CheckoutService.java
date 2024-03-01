package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.Checkout;
import com.cis.batch33.library.entity.BookISBN;
import com.cis.batch33.library.entity.LibraryMember;
import com.cis.batch33.library.model.CheckoutDTO;
import com.cis.batch33.library.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    public CheckoutDTO createCheckout(CheckoutDTO checkoutDTO) {
        Checkout checkout = mapToCheckout(checkoutDTO);
        Checkout savedCheckout = checkoutRepository.save(checkout);
        return mapToCheckoutDTO(savedCheckout);
    }

    public CheckoutDTO updateCheckout(Long checkoutId, CheckoutDTO checkoutDTO) {
        if (!checkoutRepository.existsById(checkoutId)) {
            return null;
        }
        Checkout checkoutToUpdate = mapToCheckout(checkoutDTO);
        checkoutToUpdate.setId(Math.toIntExact(checkoutId));
        Checkout updatedCheckout = checkoutRepository.save(checkoutToUpdate);
        return mapToCheckoutDTO(updatedCheckout);
    }

    public List<CheckoutDTO> getAllCheckouts() {
        List<Checkout> checkouts = checkoutRepository.findAll();
        return checkouts.stream()
                .map(this::mapToCheckoutDTO)
                .collect(Collectors.toList());
    }

    public CheckoutDTO getCheckoutById(Long checkoutId) {
        return checkoutRepository.findById(checkoutId)
                .map(this::mapToCheckoutDTO)
                .orElse(null);
    }

    private Checkout mapToCheckout(CheckoutDTO checkoutDTO) {
        Checkout checkout = new Checkout();
        checkout.setCheckoutDate(checkoutDTO.getCheckoutDate());
        checkout.setDueDate(checkoutDTO.getDueDate());
        checkout.setReturned(checkoutDTO.isReturned());
        // Assuming checkoutDTO.getMemberId() returns memberId
        LibraryMember libraryMember = new LibraryMember();
        libraryMember.setId(checkoutDTO.getMemberId());
        checkout.setLibraryMember(libraryMember);
        // Assuming checkoutDTO.getBookIsbn() returns bookIsbn
        checkout.setBookIsbn(new BookISBN(checkoutDTO.getBookIsbn()));
        // Map other fields as needed
        return checkout;
    }

    private CheckoutDTO mapToCheckoutDTO(Checkout checkout) {
        CheckoutDTO checkoutDTO = new CheckoutDTO();
        checkoutDTO.setId(checkout.getId());
        checkoutDTO.setCheckoutDate(checkout.getCheckoutDate());
        checkoutDTO.setDueDate(checkout.getDueDate());
        checkoutDTO.setReturned(checkout.isReturned());

        // Check if checkout.getLibraryMember() is not null before accessing its methods
        if (checkout.getLibraryMember() != null) {
            checkoutDTO.setMemberId(checkout.getLibraryMember().getId());
        } else {
            // Provide a default value for memberId if checkout.getLibraryMember() is null
            checkoutDTO.setMemberId(0); // Assuming memberId is of type Long
        }

        // Assuming checkout.getBookIsbn() returns BookISBN object
        if (checkout.getBookIsbn() != null) {
            checkoutDTO.setBookIsbn(checkout.getBookIsbn().getIsbn());
        } else {
            // Provide a default value for bookIsbn if checkout.getBookIsbn() is null
            checkoutDTO.setBookIsbn(0L); // Assuming bookIsbn is of type Long
        }

        // Map other fields as needed
        return checkoutDTO;
    }


}
