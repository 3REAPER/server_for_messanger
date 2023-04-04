package com.pervukhin.domain;

import com.pervukhin.dao.ProfileDao;
import com.pervukhin.dao.ProfileDaoImpl;

public class ConditionSend {
    private int id;
    private Profile profile;
    private int condition;

    public static final int CONDITION_CREATE = 0;
    public static final int CONDITION_SEND = 1;
    public static final int CONDITION_READ = 2;

    public ConditionSend(int profile, int condition) {
        this.profile = parseIntToProfile(profile);
        this.condition = condition;
    }

    public ConditionSend(int id, int profile, int condition) {
        this.id = id;
        this.profile = parseIntToProfile(profile);
        this.condition = condition;
    }

    public ConditionSend(int id, Profile profile, int condition) {
        this.id = id;
        this.profile = profile;
        this.condition = condition;
    }


    public int getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getCondition() {
        return condition;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    private static Profile parseIntToProfile(int profile) {
        try {
            ProfileDao profileDao = new ProfileDaoImpl();
            return profileDao.getById(profile);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
