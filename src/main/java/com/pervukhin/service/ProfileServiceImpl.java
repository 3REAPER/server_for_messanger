package com.pervukhin.service;

import com.pervukhin.dao.ProfileDao;
import com.pervukhin.dao.ProfileDaoImpl;
import com.pervukhin.domain.Profile;
import com.pervukhin.domain.ResultEmailAndPassword;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileServiceImpl implements ProfileService{
    private ProfileDao profileDao;

    public ProfileServiceImpl() throws SQLException, ClassNotFoundException {
        this.profileDao = new ProfileDaoImpl();
    }

    @Override
    @Transactional
    public String insert(Profile profile) {
        List<Profile> findProfiles = profileDao.getByLogin(profile.getLogin());
        if(findProfiles.size() == 0) {
            profileDao.insert(profile);
            return "Success";
        }else {
            return "LoginUsed";
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

    @Override
    public List<Profile> getByNumbers(List<String> numbers) {
        List<Profile> list = new ArrayList<>();
        for (String s: numbers){
            list.addAll(profileDao.getByNumber(s));
        }

        return list;
    }

    @Override
    @Transactional
    public ResultEmailAndPassword isRightPasswordAndLogin(String login, String password) {
        List<Profile> profiles = profileDao.getByLogin(login);
        ResultEmailAndPassword result = new ResultEmailAndPassword();
        if (profiles.isEmpty()){
            result.setResult("Логин не верный");
            return result;
        }else if (profiles.get(0).getPassword().equals(password)){
            result.setResult("true");
            result.setProfile(profiles.get(0));
            return result;
        }else {
            result.setResult("false");
            return result;
        }
    }
}
