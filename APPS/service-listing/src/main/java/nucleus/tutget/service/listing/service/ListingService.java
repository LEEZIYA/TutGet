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

    public AllListing getSearchListings(String searchKey) {
        AllListing allListing = new AllListing();
        List<Listing> listings = new ArrayList<>();     
        System.out.println("ITS IS WITHIN THE FUNCTION getSearchListings");
        if(searchKey == null){
            listingRepository.findAll().forEach(listings::add);
            allListing.setAllListing(listings);
            return allListing;
        }
        else{
            listingRepository.findByIdContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey,searchKey).forEach(listings::add);
            System.out.println("Listings : " + listings.toString());
            allListing.setAllListing(listings);  
            return allListing;
        }
    }

    // public AllListing getSearchListingsAll(Object requestData) {
    //     // AllListing allListing = new AllListing();
    //     // List<Listing> listings = new ArrayList<>();     
    //     // System.out.println("ITS IS WITHIN THE FUNCTION getSearchListingsAll");

    //     for (Listing listing : requestData.getListings()) {
    //         if (listing == null) {
    //             allNotNull = false;
    //             break;
    //         }
    //     }
        

        
    //     if(requestData == null){
    //         listingRepository.findAll().forEach(listings::add);
    //         allListing.setAllListing(listings);
    //         return allListing;
    //     }
    //     else{
    //         listingRepository.findByIdContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey,searchKey).forEach(listings::add);
    //         System.out.println("Listings : " + listings.toString());
    //         allListing.setAllListing(listings);  
    //         return allListing;
    //     }
    // }

}
