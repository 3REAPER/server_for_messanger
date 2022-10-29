package com.pervukhin.rest;

import com.pervukhin.domain.Profile;
import com.pervukhin.domain.ResultEmailAndPassword;
import com.pervukhin.service.ProfileService;
import com.pervukhin.service.ProfileServiceImpl;
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
                              @RequestParam String password,
                              @RequestParam String number) throws SQLException, ClassNotFoundException {
            Profile profile = new Profile(
                    name,
                    login,
                    password,
                    number);

            return profileService.insert(profile);
    }

    @PostMapping("/profile/{id}")
    public String updateProfile(
            @PathVariable int id ,
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String number) throws SQLException, ClassNotFoundException {
        Profile profile = new Profile(
                id,
                name,
                login,
                password,
                number);

        return profileService.update(profile);
    }

    @DeleteMapping("/profile/{id}")
    public String deleteProfile(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return profileService.delete(id);
    }

    @GetMapping("/profile/{login}/{password}")
    public ResultEmailAndPassword isRightPasswordAndLogin(@PathVariable String login, @PathVariable String password){
        return profileService.isRightPasswordAndLogin(login,password);
    }
}
