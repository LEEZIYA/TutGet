package com.tutget.tutgetmain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutget.tutgetmain.model.Listing;
import com.tutget.tutgetmain.model.Payment;
import com.tutget.tutgetmain.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {
     
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/payment/{id}")
    public Listing getListingDetails(@PathVariable String id){
         return paymentService.getListingDetails(id);
    }

    @PutMapping("/payment")
    public void updateListing(@RequestBody Listing listing){
        paymentService.updateListing(listing);
    }

    @PostMapping("/payment/addPayment")
    public String addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

}
