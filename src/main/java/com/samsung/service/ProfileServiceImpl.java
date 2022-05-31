package com.samsung.service;

import com.samsung.dao.ProfileDao;
import com.samsung.dao.ProfileDaoImpl;
import com.samsung.domain.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public class ProfileServiceImpl implements ProfileService{
    private ProfileDao profileDao;

    public ProfileServiceImpl() throws SQLException, ClassNotFoundException {
        this.profileDao = new ProfileDaoImpl();
    }

    @Override
    @Transactional
    public String insert(Profile profile) {
        if(profileDao.getByLogin(profile.getLogin()) != null) {
            profileDao.insert(profile);
            return "Success";
        }else {
            return "Login used";
        }
    }

    @Override
    @Transactional
    public String update(Profile profile) {
        try {
            profileDao.update(profile);
            return "Success";
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    @Transactional
    public String delete(int id) {
        try {
            profileDao.delete(id);
            return "Success";
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    @Transactional
    public Profile getById(int id) {
        return profileDao.getById(id);
    }

    @Override
    @Transactional
    public List<Profile> getByName(String name) {
        return profileDao.getByName(name);
    }

    @Override
    @Transactional
    public List<Profile> getByLogin(String login) {
        return profileDao.getByLogin(login);
    }
}
