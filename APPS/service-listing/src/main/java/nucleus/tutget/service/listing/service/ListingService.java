package nucleus.tutget.service.listing.service;

import nucleus.tutget.service.listing.model.AllListing;
import nucleus.tutget.service.listing.model.Listing;
import nucleus.tutget.service.listing.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public  AllListing getAllListings(){
        AllListing allListing = new AllListing();
        List<Listing> listings = new ArrayList<>();
        listingRepository.findAll().forEach(listings::add);
        allListing.setAllListing(listings);
        return allListing;
    }

    public Optional<Listing> getListing(String id){
        return listingRepository.findById(id);
    }

    public String addListing(Listing listing) {
        listingRepository.save(listing);
        return listing.getId();
    }

    public void updateListing(Listing listing) {
        listingRepository.save(listing);
    }

    public void deleteListing(String id) {
        listingRepository.deleteById(id);
    }

    public AllListing getAllListingsByDescription(String description){
        AllListing allListing = new AllListing();
        List<Listing> listings = new ArrayList<>();
        listingRepository.findByDescription(description).forEach(listings::add);
        allListing.setAllListing(listings);
        return allListing;

    }

}
