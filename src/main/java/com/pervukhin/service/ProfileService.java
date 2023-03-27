package com.pervukhin.service;

import com.pervukhin.domain.Profile;
import com.pervukhin.domain.ResultEmailAndPassword;


import java.util.List;

public interface ProfileService {

    String insert(Profile profile);

    String update(Profile profile);

    String delete(int id);

    Profile getById(int id);

    List<Profile> getByName(String name);

    List<Profile> getByLogin(String login);

    List<Profile> getByNumbers(List<String> numbers);

    ResultEmailAndPassword isRightPasswordAndLogin(String login, String password);
}
