package com.tutget.tutgetmain.service;

import com.tutget.tutgetmain.model.profile.ProfileList;
import com.tutget.tutgetmain.model.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    private String microserviceURL = "http://profile-service/users";

    public List<Profile> getAllProfiles() {
        ProfileList profileList = restTemplate.getForObject(microserviceURL, ProfileList.class);
        return profileList.getProfileList();
    }

    public Profile getProfile(String id) {
        return restTemplate.getForObject(microserviceURL + "/" + id, Profile.class);
    }

    public Profile login(Profile loginProfile) {
//        ProfileList profilelist;
        Profile profile;
        if(loginProfile.getUserID()==null){
            return loginProfile;
        }
        profile = restTemplate.postForObject(microserviceURL + "/login", loginProfile ,Profile.class);
//        profile = profilelist.getProfileList().get(0);

        if (profile.getAuthenticateStatus()) {
            return profile;

//        profile = restTemplate.getForObject(microserviceURL + "/userId/" + loginProfile.getUserID(), Profile.class);
////        profile = profilelist.getProfileList().get(0);
//
//        if (profile!= null && profile.getPassword()!=null && profile.getPassword().equals(loginProfile.getPassword())) {
//            return profile;
        } else {
            return loginProfile;
        }

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


