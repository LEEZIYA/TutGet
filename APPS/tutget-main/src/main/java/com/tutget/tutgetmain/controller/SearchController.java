package com.tutget.tutgetmain.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutget.tutgetmain.model.AllListing;
import com.tutget.tutgetmain.model.Listing;
import com.tutget.tutgetmain.service.SearchService;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    
    @GetMapping("/all/all/{requestData}")    
    public AllListing getSearchResultAll(@PathVariable("requestData") Object requestData){
        return searchService.searchAllAll(requestData);
    }

    @GetMapping("/all/{searchKey}")    
    public AllListing getSearchResult(@PathVariable("searchKey") String searchKey, HttpServletRequest request){

        System.out.println("THE USER ID IS: " + SecurityContextHolder.getContext().getAuthentication().getName());


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
