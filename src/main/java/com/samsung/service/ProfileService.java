package com.samsung.service;

import com.samsung.domain.Profile;
import com.samsung.domain.Profile;


import java.util.List;

public interface ProfileService {

    String insert(Profile profile);

    String update(Profile profile);

    String delete(int id);

    Profile getById(int id);

    List<Profile> getByName(String name);

    List<Profile> getByLogin(String login);
}
