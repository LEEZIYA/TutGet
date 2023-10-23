package nucleus.tutget.service.payment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nucleus.tutget.service.payment.model.Listing;
import nucleus.tutget.service.payment.model.Payment;
import nucleus.tutget.service.payment.service.PaymentService;

@RestController
@RequestMapping
public class Controller {
    
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/payment")
    public String addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    @GetMapping("/payment/getPayment/{transactionId}")
    public Optional<Payment> getPayment(@PathVariable("transactionId") String transactionalId){
        return paymentService.getPayment(transactionalId);
    }

    @GetMapping("/payment/{id}")    
    public Listing getListingDetails(@PathVariable("id") String id){
        ResponseEntity<Listing> listingResp =  restTemplate.exchange("http://localhost:8091/listings/{id}", HttpMethod.GET, null, Listing.class,id);
        Listing listing = listingResp.getBody();

        if (id != null) {
            return (listing);
        } else {
            System.out.println("No results");
            return null;
        }
    }

    @PutMapping("/payment")    
    public void updateListing(@RequestBody Listing listing){
        restTemplate.put("http://localhost:8091/listings", listing, Listing.class);
    }
}