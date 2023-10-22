package nucleus.tutget.service.servicesearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nucleus.tutget.service.servicesearch.model.AllListing;
import nucleus.tutget.service.servicesearch.model.Listing;
import nucleus.tutget.service.servicesearch.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    
    @GetMapping("/all/all/{requestData}")    
    public AllListing getSearchResultAll(@PathVariable("requestData") Object requestData){
        return searchService.searchAllAll(requestData);
    }
    @GetMapping("/all/{searchKey}")    
    public AllListing getSearchResult(@PathVariable("searchKey") String searchKey){
        return searchService.searchAll(searchKey);
    }

    @GetMapping("/{id}")    
    public Listing getListing(@PathVariable("id") String id){
        return searchService.searchId(id);
        // ResponseEntity<Listing> listingResp =  restTemplate.exchange("http://localhost:8091/listings/{id}", HttpMethod.GET, null, Listing.class,id);
        // Listing listing = listingResp.getBody();

        // if (id != null) { 
        //     return (listing);
        // } else {
        //     System.out.println("No results");
        //     return null; 
        // }
        
    }

    // @GetMapping("/d/{description}")    
    // public AllListing getListingsByDescription(@PathVariable String description){
    //     ResponseEntity<AllListing> listingResp =  restTemplate.exchange("http://localhost:8091/listings/find/{description}", HttpMethod.GET, null, AllListing.class,description);
    //     AllListing listing = listingResp.getBody();

    //     if (description != null) { 
    //         return (listing);
    //     } else {
    //         System.out.println("No results");
    //         return null; 
    //     }
    
    // }
}
