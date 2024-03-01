package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.Address;
import com.cis.batch33.library.entity.BookISBN;
import com.cis.batch33.library.entity.Checkout;
import com.cis.batch33.library.entity.LibraryMember;
import com.cis.batch33.library.model.AddressDTO;
import com.cis.batch33.library.model.CheckoutDTO;
import com.cis.batch33.library.model.Member;
import com.cis.batch33.library.repository.LibraryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private LibraryMemberRepository memberRepository;

    @Transactional
    public Member createMember(Member member) {
        if (member == null || member.getAddress() == null) {
            throw new IllegalArgumentException("Member and Address cannot be null");
        }

        LibraryMember libraryMember = new LibraryMember();
        mapToLibraryMember(libraryMember, member);
        LibraryMember createdMember = memberRepository.save(libraryMember);
        return mapToMember(createdMember);
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        Optional<LibraryMember> memberOptional = memberRepository.findById(Math.toIntExact(memberId));
        return memberOptional.map(this::mapToMember).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        List<LibraryMember> libraryMembers = memberRepository.findAll();
        return libraryMembers.stream()
                .map(this::mapToMember)
                .collect(Collectors.toList());
    }

    @Transactional
    public Member updateMember(Long memberId, Member memberDetails) {
        Optional<LibraryMember> memberOptional = memberRepository.findById(Math.toIntExact(memberId));
        if (!memberOptional.isPresent()) {
            throw new RuntimeException("Member not found");
        }
        LibraryMember libraryMember = memberOptional.get();
        mapToLibraryMember(libraryMember, memberDetails);
        LibraryMember updatedMember = memberRepository.save(libraryMember);
        return mapToMember(updatedMember);
    }

    @Transactional
    public boolean deleteMember(Long memberId) {
        if (memberRepository.existsById(Math.toIntExact(memberId))) {
            memberRepository.deleteById(Math.toIntExact(memberId));
            return true;
        }
        return false;
    }

    private void mapToLibraryMember(LibraryMember libraryMember, Member member) {
        libraryMember.setFirstName(member.getFirstName());
        libraryMember.setLastName(member.getLastName());
        libraryMember.setEmailAddress(member.getEmailAddress());
        libraryMember.setPhoneNumber(member.getPhoneNumber());
        libraryMember.setMembershipLevel(member.getMembershipLevel());

        if (member.getAddress() != null) {
            Address address = new Address();
            mapToAddress(address, member.getAddress());
            libraryMember.setAddress(address);
            libraryMember.setAddressId(address.getAddressId()); // Set the addressId
        }

        if (member.getCheckouts() != null) {
            List<Checkout> checkouts = member.getCheckouts().stream()
                    .map(this::mapToCheckout)
                    .collect(Collectors.toList());
            libraryMember.setCheckouts(checkouts);
        }
    }

    private void mapToAddress(Address address, AddressDTO addressDTO) {
        address.setLine1(addressDTO.getLine1());
        address.setLine2(addressDTO.getLine2());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZip(addressDTO.getZip());
    }

    private Member mapToMember(LibraryMember libraryMember) {
        Member member = new Member();
        member.setMemberId(libraryMember.getMemberId());
        member.setFirstName(libraryMember.getFirstName());
        member.setLastName(libraryMember.getLastName());
        member.setEmailAddress(libraryMember.getEmailAddress());
        member.setPhoneNumber(libraryMember.getPhoneNumber());
        member.setMembershipLevel(libraryMember.getMembershipLevel());

        if (libraryMember.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            mapToAddressDTO(addressDTO, libraryMember.getAddress());
            member.setAddress(addressDTO);
        }

        if (libraryMember.getCheckouts() != null) {
            List<CheckoutDTO> checkoutDTOs = libraryMember.getCheckouts().stream()
                    .map(this::mapToCheckoutDTO)
                    .collect(Collectors.toList());
            member.setCheckouts(checkoutDTOs);
        }

        return member;
    }

    private void mapToAddressDTO(AddressDTO addressDTO, Address address) {
        addressDTO.setLine1(address.getLine1());
        addressDTO.setLine2(address.getLine2());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZip(address.getZip());
    }

    private Checkout mapToCheckout(CheckoutDTO checkoutDTO) {
        Checkout checkout = new Checkout();
        checkout.setId(checkoutDTO.getId());
        // Assuming checkoutDTO.getIsbn() returns the ISBN of the book
        BookISBN bookIsbn = new BookISBN(checkoutDTO.getBookIsbn());
        bookIsbn.setIsbn(checkoutDTO.getIsbn());
        checkout.setBookIsbn(bookIsbn);
        checkout.setCheckoutDate(checkoutDTO.getCheckoutDate());
        checkout.setDueDate(checkoutDTO.getDueDate());
        checkout.setReturned(checkoutDTO.isReturned());
        return checkout;
    }



    private CheckoutDTO mapToCheckoutDTO(Checkout checkout) {
        CheckoutDTO checkoutDTO = new CheckoutDTO();
        checkoutDTO.setId(checkout.getId());
        // Assuming checkout.getBookIsbn() returns the ISBN of the book
        checkoutDTO.setIsbn(checkout.getBookIsbn().getIsbn());
        checkoutDTO.setCheckoutDate(checkout.getCheckoutDate());
        checkoutDTO.setDueDate(checkout.getDueDate());
        checkoutDTO.setReturned(checkout.isReturned());
        return checkoutDTO;
    }

}
