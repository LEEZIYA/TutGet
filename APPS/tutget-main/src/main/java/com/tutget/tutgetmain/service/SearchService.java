package com.tutget.tutgetmain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutget.tutgetmain.model.AllListing;
import com.tutget.tutgetmain.model.Listing;

@EnableDiscoveryClient
@Service
public class SearchService {

    private String listingmicroserviceURL = "http://listing-service/listings";
    //private String listingmicroserviceURL = "http://localhost:8091/listings";
    //private String qnamicroserviceURL = "http://qna-service/qna";

    @Autowired
    private RestTemplate restTemplate;


    public Listing searchId(String id) {
        ResponseEntity<Listing> listingResp =  restTemplate.exchange(listingmicroserviceURL + "/"+ id , HttpMethod.GET, null, Listing.class,id);
        //ResponseEntity<Listing> listingResp =  restTemplate.exchange(discoveryClient.getNextServerFromEureka("listing-service", false).getHomePageUrl() +"listings/" + id , HttpMethod.GET, null, Listing.class,id);
        Listing listing = listingResp.getBody();

        if (id != null) { 
            return (listing);
        } else {
            System.out.println("No results");
            return null; 
        }
    }

    public AllListing searchAll(String searchKey) {
        ResponseEntity<AllListing> listingResp =  restTemplate.exchange(listingmicroserviceURL + "/find/"+ searchKey , HttpMethod.GET, null, AllListing.class,searchKey);
        //ResponseEntity<Listing> listingResp =  restTemplate.exchange(discoveryClient.getNextServerFromEureka("listing-service", false).getHomePageUrl() +"listings/" + id , HttpMethod.GET, null, Listing.class,id);
        AllListing listings = listingResp.getBody();

        if (searchKey != null) { 
            return (listings);
        } else {
            System.out.println("No results");
            return null; 
        }
    }

    public AllListing searchAllAll(Object requestData) {
        ResponseEntity<AllListing> listingResp =  restTemplate.exchange(listingmicroserviceURL + "/find/all/"+ requestData , HttpMethod.GET, null, AllListing.class,requestData);
        //ResponseEntity<Listing> listingResp =  restTemplate.exchange(discoveryClient.getNextServerFromEureka("listing-service", false).getHomePageUrl() +"listings/" + id , HttpMethod.GET, null, Listing.class,id);
        AllListing listings = listingResp.getBody();

        if (requestData != null) { 
            return (listings);
        } else {
            System.out.println("No results");
            return null; 
        }
    }
        // List<SearchResult> results = new ArrayList<>();

        // Perform a search on the listing microservice
        // List<ListingResult> listingResults = listingService.searchInListingMicroservice(query);
        // results.addAll(listingResults);

        // Perform a search on the Q&A microservice
        // List<QnaResult> qnaResults = qnaService.searchInQnaMicroservice(query);
        // results.addAll(qnaResults);
}