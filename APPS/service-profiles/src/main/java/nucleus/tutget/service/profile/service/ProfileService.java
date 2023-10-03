package nucleus.tutget.service.profile.service;

import nucleus.tutget.service.profile.model.ProfileList;
import nucleus.tutget.service.profile.model.Profile;
import nucleus.tutget.service.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

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

    public ProfileList getProfileByUserID(String userID) {
        ProfileList profileList = new ProfileList();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByUserID(userID).forEach(profiles::add);
        profileList.setProfileList(profiles);
        return profileList;
    }

    public Profile addProfile(Profile profile) {
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
        profileRepository.findByDescription(description).forEach(profiles::add);
        profileList.setProfileList(profiles);
        return profileList;

    }



    public ProfileList getProfileListByName(String name) {
        ProfileList profileList = new ProfileList();
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findByFirstName(name).forEach(profiles::add);
        profileRepository.findByLastName(name).forEach(profiles::add);
        profileList.setProfileList(profiles);
        return profileList;

    }
}
