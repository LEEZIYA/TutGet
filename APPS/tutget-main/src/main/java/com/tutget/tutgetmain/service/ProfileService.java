package com.tutget.tutgetmain.service;

import com.tutget.tutgetmain.constants.AuthConstants;
import com.tutget.tutgetmain.model.profile.AuthResult;
import com.tutget.tutgetmain.model.profile.ProfileList;
import com.tutget.tutgetmain.model.profile.Profile;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

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

    public AuthResult login(Profile loginProfile) {
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

        if (profile != null && "true".equalsIgnoreCase(profile.authenticateStatus())) {
            System.out.println(profile.toString());
            return new AuthResult(Jwts.builder()
              .subject(profile.userID())
              .claim("id", profile.id())
              .issuer("TutGet")
              .expiration(Date.from(LocalDateTime.now().plusHours(AuthConstants.AUTH_EXPIRY_IN_SECONDS.longValue() / 60 / 60).atZone(ZoneId.systemDefault()).toInstant())) // 1 hour
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


