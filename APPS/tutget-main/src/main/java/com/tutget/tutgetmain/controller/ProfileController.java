package com.tutget.tutgetmain.controller;

import com.tutget.tutgetmain.constants.AuthConstants;
import com.tutget.tutgetmain.dto.ProfileDTO;
import com.tutget.tutgetmain.mapper.ProfileMapper;
import com.tutget.tutgetmain.model.profile.AuthResult;
import com.tutget.tutgetmain.model.profile.Profile;
import com.tutget.tutgetmain.service.ProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @GetMapping("/users")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @GetMapping("/users/{id}")
    public Profile getProfile(@PathVariable String id){
        System.out.println("id" + id);
        String contextId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        System.out.println("contextId" + contextId);
        return profileService.getProfile(contextId);
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthResult> loginUser(@RequestBody Profile profile, @RequestHeader(value = "Authorization", required = false) String authHeader, HttpServletResponse response){
        System.out.println("Profile: " + profile);
        System.out.println("Auth Header: " + authHeader);
        AuthResult loginResult = profileService.login(profile, authHeader);

        if (loginResult == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

//        Cookie authCookie = new Cookie("authCookie", loginResult.jwt());
//        authCookie.setMaxAge(AuthConstants.AUTH_EXPIRY_IN_SECONDS); // 1 hour
//        System.out.println("ProfileController: " + authCookie.getMaxAge());
//        authCookie.setPath("/");
//        authCookie.setHttpOnly(true);
//        authCookie.setSecure(true);

        response.addHeader("Set-Cookie", "authCookie=" + loginResult.jwt() + "; SameSite=None; Secure; Path=/; HttpOnly; Max-Age=" + AuthConstants.AUTH_EXPIRY_IN_SECONDS);
//        response.addCookie(authCookie);

        return ResponseEntity.ok(new AuthResult(null, loginResult.userType(), loginResult.acadLvl()));
    }

    @GetMapping("/users/logout")
    public ResponseEntity<Void> logoutUser(HttpServletResponse response) {
//        Cookie authCookie = new Cookie("authCookie", null);
//        authCookie.setMaxAge(0);
//        authCookie.setPath("/");
//        authCookie.setHttpOnly(true);
//        authCookie.setSecure(true);
        response.addHeader("Set-Cookie", "authCookie=" + null + "; SameSite=None; Secure; Path=/; HttpOnly; Max-Age=" + 0);
//        response.addCookie(authCookie);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/users/userId")
    public ProfileDTO getProfile() {
        String contextId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        System.out.println("contextId" + contextId);
        Profile profile = profileService.getProfile(contextId);
        return ProfileMapper.INSTANCE.toDTO(profile);
    }

    @GetMapping("/users/userId/{userID}")
    public Profile getProfileByUserID(@PathVariable String userID){
        System.out.println("user id" + userID);
        String contextId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        System.out.println("contextId" + contextId);
        return userID != null ? profileService.getProfileByUserID(userID) : profileService.getProfile(contextId);

    }

    @PostMapping("/users")
    public Profile addUser(@RequestBody Profile profile){
        return profileService.addProfile(profile);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody Profile profile){
        profileService.updateProfile(profile);
    }
    @DeleteMapping("/users/{id}")
    public void deleteListing(@PathVariable String id){
        profileService.deleteProfile(id);
    }

    @GetMapping("/users/findByDescription/{desciption}")
    public List<Profile> getProfilesByDescription(@PathVariable String description){
        return profileService.getProfileListByDescription(description);
    }

    @GetMapping("/users/findByName/{name}")
    public List<Profile> getProfileByName(@PathVariable String name){
        return profileService.getProfileListByName(name);
    }





}