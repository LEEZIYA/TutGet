package com.tutget.tutgetmain.service;

import com.tutget.tutgetmain.model.profile.AuthResult;
import com.tutget.tutgetmain.model.profile.ProfileList;
import com.tutget.tutgetmain.model.profile.Profile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OAuthService oAuthService;

    private String microserviceURL = "http://profile-service/users";

    @Value("${jjwt.key}")
    private String jjwtKey;

    public List<Profile> getAllProfiles() {
        ProfileList profileList = restTemplate.getForObject(microserviceURL, ProfileList.class);
        return profileList.getProfileList();
    }

    public Profile getProfile(String id) {
        return restTemplate.getForObject(microserviceURL + "/" + id, Profile.class);
    }

    public AuthResult login(Profile loginProfile, String authHeader) {
        if (authHeader != null) {   // for login via Keycloak
            String token = authHeader.replace("Bearer ", "");

            Claims claims = null;
            try {
                PublicKey key = oAuthService.getPublicKey();
                System.out.println("OAuth Public Key: " + key);

                claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
                System.out.println("Verified Claims: " + claims);
            } catch (Exception e) {
                System.err.println("Invalid token: " + e.getMessage());
                return null;
            }

            String subject = claims.getSubject();
            String firstName = claims.get("given_name", String.class);
            String lastName = claims.get("family_name", String.class);

            Profile p = new Profile(
                null, 
                "J1", 
                subject, 
                "", 
                "S", 
                firstName, 
                lastName, 
                "98765432", 
                "Home", 
                "123456", 
                "New Google user", 
                "");

            if (getProfileByUserID(subject) != null) {
                Profile returnedUser = addProfile(p);
                System.out.println(returnedUser);
            }

            loginProfile = p;
        }

        if (loginProfile.userID() == null) {
            // return loginProfile
            return null;
        }

        Profile profile;
        profile = restTemplate.postForObject(microserviceURL + "/login", loginProfile ,Profile.class);
        // profile = profilelist.getProfileList().get(0);
        // if (profile.getAuthenticateStatus()) {
        //    return profile;
//        System.out.println("Login jwt key: " + jjwtKey);

        if (profile != null && profile.authenticateStatus().equals("true")) {
            System.out.println(profile.toString());
            return new AuthResult(Jwts.builder()
              .subject(profile.userID())
              .claim("id", profile.id())
              .issuer("TutGet")
              .expiration(Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant())) // 1 hour
              .signWith(new SecretKeySpec(jjwtKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
              .compact(), profile.userType(), profile.acadLvl());
        }

        return null;
    }


    public Profile addProfile(Profile profile) {
        return restTemplate.postForObject(microserviceURL, profile, Profile.class);
    }

    public void updateProfile(Profile profile) {
        restTemplate.put(microserviceURL, profile, Profile.class);
    }

    public void deleteProfile(String id) {
        restTemplate.delete(microserviceURL + '/' + id);
    }

    public List<Profile> getProfileListByDescription(String description) {
        ProfileList profileList = restTemplate.getForObject(microserviceURL + "/findByDescription/" + description, ProfileList.class);
        return profileList.getProfileList();

    }

    public Profile getProfileByUserID(String userID) {
        Profile profile = restTemplate.getForObject(microserviceURL + "/userId/" + userID, Profile.class);
//        return profileList.getProfileList();
        return profile;
    }

    public List<Profile> getProfileListByName(String name) {
        ProfileList profileList = restTemplate.getForObject(microserviceURL + "/findByName/" + name, ProfileList.class);
        return profileList.getProfileList();
    }

}


