package nucleus.tutget.service.profile.controller;

import nucleus.tutget.service.profile.model.Profile;
import nucleus.tutget.service.profile.model.ProfileList;
import nucleus.tutget.service.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.Optional;



@RestController
@RequestMapping
public class Controller {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/users")
    public ProfileList getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @GetMapping("/users/test")
    public String getTestProfile(){
        return "profile service is up";
    }

    @GetMapping("/users/{id}")
    public Optional<Profile> getProfile(@PathVariable String id){
        return profileService.getProfile(id);
    }

    @GetMapping("/users/userId/{userID}")
    public ProfileList getProfileByUserID(@PathVariable String userID){
        return profileService.getProfileByUserID(userID);
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
    public ProfileList getProfilesByDescription(@PathVariable String description){
        return profileService.getProfileListByDescription(description);
    }

    @GetMapping("/users/findByName/{name}")
    public ProfileList getProfileByName(@PathVariable String name){
        return profileService.getProfileListByName(name);
    }




}