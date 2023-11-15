package nucleus.tutget.service.listing.controller;

import nucleus.tutget.service.listing.model.AllListing;
import nucleus.tutget.service.listing.model.Listing;
import nucleus.tutget.service.listing.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private ListingService listingService;

    @RequestMapping("/listings")
    public AllListing getAllListings(){
        return listingService.getAllListings();
    }

    @RequestMapping("/listings/{id}")
    public Optional<Listing> getListing(@PathVariable String id){
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
    public AllListing getListingsByDescription(@PathVariable String description){
        return listingService.getAllListingsByDescription(description);
    }

    @GetMapping("/listings/find/{searchKey}")
    public AllListing getSearchListings(@PathVariable(required=false) String searchKey){
        return listingService.getSearchListings(searchKey);
    }

    // @GetMapping("/listings/find/all/{requestData}")
    // public AllListing getSearchListings(@PathVariable(required=false) Object requestData){
    //     return listingService.getSearchListingsAll(requestData);
    // }

}
