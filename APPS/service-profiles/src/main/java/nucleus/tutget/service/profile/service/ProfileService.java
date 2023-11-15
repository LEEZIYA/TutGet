package nucleus.tutget.service.profile.service;

import nucleus.tutget.service.profile.model.ProfileList;
import nucleus.tutget.service.profile.model.Profile;
import nucleus.tutget.service.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Service
public class ProfileService {

    @Bean
    public static PasswordEncoder getPassowrdEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileRepository profileRepository;

    public  ProfileList getAllProfiles(){
        ProfileList allProfile = new ProfileList();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(profiles::add);
        allProfile.setProfileList(profiles);
        return allProfile;
    }

    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id);
    }

    public Profile login(Profile loginProfile) {
////        ProfileList profileList = new ProfileList();
//        List<Profile> profiles = new ArrayList<>();
//        profileRepository.findByUserID(userID).forEach(profiles::add);
//        profileList.setProfileList(profiles);
//        return profileList;
        Profile profile = null;
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByUserID(loginProfile.getUserID()).forEach(profiles::add);
        if(profiles.size()>0){
            profile = profiles.get(0);
        }
        if (profile!=null && passwordEncoder.matches(loginProfile.getPassword(),profile.getPassword())){
            profile.setAuthenticateStatus(true);
            return profile;
        }
        else{
            return loginProfile;
        }
//        profileList.setProfileList(profiles);

        //return loginProfile;
    }

    public Profile getProfileByUserID(String userID) {
////        ProfileList profileList = new ProfileList();
//        List<Profile> profiles = new ArrayList<>();
//        profileRepository.findByUserID(userID).forEach(profiles::add);
//        profileList.setProfileList(profiles);
//        return profileList;

        Profile profile = new Profile();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByUserID(userID).forEach(profiles::add);
        if(profiles.size()>0){
            profile = profiles.get(0);
        }

//        profileList.setProfileList(profiles);
        return profile;
    }

    public Profile addProfile(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
        return profile;
    }

    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public void deleteProfile(String id) {
        profileRepository.deleteById(id);
    }

    public ProfileList getProfileListByDescription(String description) {
        ProfileList profileList = new ProfileList();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByDescriptionContainingIgnoreCase(description).forEach(profiles::add);
        profileList.setProfileList(profiles);
        return profileList;

    }



    public ProfileList getProfileListByName(String name) {
        ProfileList profileList = new ProfileList();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name,name).forEach(profiles::add);
//        profileRepository.findByFirstName(name).forEach(profiles::add);
//        profileRepository.findByLastName(name).forEach(profiles::add);
        profileList.setProfileList(profiles);
        return profileList;

    }

}
