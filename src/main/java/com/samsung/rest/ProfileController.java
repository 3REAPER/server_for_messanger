package com.samsung.rest;

import com.samsung.domain.Profile;
import com.samsung.service.ProfileService;
import com.samsung.service.ProfileServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ProfileController  {
    private final ProfileService profileService;

    public ProfileController() throws SQLException, ClassNotFoundException {
        this.profileService = new ProfileServiceImpl();
    }


    @GetMapping("/profile/{id}")
    public Profile getProfileById(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return profileService.getById(id);
    }

    @PostMapping("/profile")
    public String createProfile(@RequestParam String name,
                              @RequestParam String login,
                              @RequestParam String password) throws SQLException, ClassNotFoundException {
            Profile profile = new Profile(
                    name,
                    login,
                    password);

            return profileService.insert(profile);
    }

    @PostMapping("/profile/{id}")
    public String updateProfile(
            @PathVariable int id ,
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password) throws SQLException, ClassNotFoundException {
        Profile profile = new Profile(
                id,
                name,
                login,
                password);

        return profileService.update(profile);
    }

    @DeleteMapping("/profile/{id}")
    public String deleteProfile(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return profileService.delete(id);
    }
}
