package com.tutget.tutgetmain.controller;

import com.tutget.tutgetmain.model.Listing;
import com.tutget.tutgetmain.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @RequestMapping("/listings")
    public List<Listing> getAllListings(){
        return listingService.getAllListings();
    }

    @RequestMapping("/listings/{id}")
    public Listing getListing(@PathVariable String id){
         return listingService.getListing(id);
    }

    @PostMapping("/listings")
    public String addListing(@RequestBody Listing listing){
        return listingService.addListing(listing);
    }

    @PutMapping("/listings")
    public void updateListing(@RequestBody Listing listing){
        listingService.updateListing(listing);
    }
    @DeleteMapping("/listings/{id}")
    public void deleteListing(@PathVariable String id){
        listingService.deleteListing(id);
    }
    @RequestMapping("/listings/find/{description}")
    public List<Listing> getListingsByDescription(@PathVariable String description){
        return listingService.getAllListingsByDescription(description);
    }


}
