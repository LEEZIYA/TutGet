package com.tutget.tutgetmain.service;

import com.tutget.tutgetmain.model.AllListing;
import com.tutget.tutgetmain.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ListingService {

    @Autowired
    private RestTemplate restTemplate;

    private String microserviceURL = "http://gateway/listings";

    public List<Listing> getAllListings(){
        AllListing allListing = restTemplate.getForObject(microserviceURL, AllListing.class);
        return allListing.getAllListing();
    }
    public Listing getListing(String id){
        return restTemplate.getForObject(microserviceURL + "/" + id, Listing.class);
    }

    public String addListing(Listing listing) {
        return restTemplate.postForObject(microserviceURL, listing, String.class);
    }

    public void updateListing(Listing listing) {
        System.out.println("Update listing: " + listing.getAssignedTutorId());
        restTemplate.put(microserviceURL, listing, Listing.class);
    }

    public void deleteListing(String id) {
        restTemplate.delete(microserviceURL + '/' + id);
    }

    public List<Listing> getAllListingsByDescription(String description){
        AllListing allListing = restTemplate.getForObject(microserviceURL + "/find/" + description, AllListing.class);
        return allListing.getAllListing();
    }
}
