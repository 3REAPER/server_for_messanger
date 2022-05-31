package com.samsung.dao;

import com.samsung.domain.Profile;

import java.util.List;

public interface ProfileDao {

    void insert(Profile profile);

    void update(Profile profile);

    void delete(int id);

    Profile getById(int id);

    List<Profile> getByName(String name);

    List<Profile> getByLogin(String login);
}
