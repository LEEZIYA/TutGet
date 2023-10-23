package com.tutget.tutgetmain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutget.tutgetmain.model.Listing;
import com.tutget.tutgetmain.model.Payment;

@Service
public class PaymentService {
    @Autowired
    private RestTemplate restTemplate;

    private String microserviceURL = "http://payment-service/payment";

   
    public Listing getListingDetails(String id){
        return restTemplate.getForObject(microserviceURL + "/" + id, Listing.class);
    }

    public void updateListing(Listing listing) {
        restTemplate.put(microserviceURL, listing, Listing.class);
    }

    public String addPayment(Payment payment) {
        return restTemplate.postForObject(microserviceURL, payment, String.class);
    }
}
