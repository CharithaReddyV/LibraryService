package com.cis.batch33.library.controller;

import com.cis.batch33.library.model.CheckoutDTO;
import com.cis.batch33.library.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkouts")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutDTO> createCheckout(@RequestBody CheckoutDTO checkoutDTO) {
        CheckoutDTO createdCheckout = checkoutService.createCheckout(checkoutDTO);
        return new ResponseEntity<>(createdCheckout, HttpStatus.CREATED);
    }

    @PutMapping("/{checkoutId}")
    public ResponseEntity<CheckoutDTO> updateCheckout(@PathVariable Integer checkoutId, @RequestBody CheckoutDTO checkoutDTO) {
        CheckoutDTO updatedCheckout = checkoutService.updateCheckout(Long.valueOf(checkoutId), checkoutDTO);
        if (updatedCheckout != null) {
            return new ResponseEntity<>(updatedCheckout, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CheckoutDTO>> getAllCheckouts() {
        List<CheckoutDTO> checkouts = checkoutService.getAllCheckouts();
        return new ResponseEntity<>(checkouts, HttpStatus.OK);
    }

    @GetMapping("/{checkoutId}")
    public ResponseEntity<CheckoutDTO> getCheckoutById(@PathVariable Integer checkoutId) {
        CheckoutDTO checkout = checkoutService.getCheckoutById(Long.valueOf(checkoutId));
        if (checkout != null) {
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
