package com.samsung.domain;

import com.samsung.dao.ProfileDao;
import com.samsung.dao.ProfileDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat {
    private int id;
    private String name;
    private String isPrivate;
    private List<Profile> usersId;

    public Chat(int id, String name, String usersId, String isPrivate) throws SQLException, ClassNotFoundException {
        this.id = id;
        this.name = name;
        this.usersId = parseStringToListInt(usersId);
        this.isPrivate = isPrivate;

    }

    public Chat(String name, String usersId, String isPrivate) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.usersId = parseStringToListInt(usersId);
        this.isPrivate = isPrivate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Profile> getUsersId() {
        return usersId;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsersId(List<Profile> usersId) {
        this.usersId = usersId;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public static List<Profile> parseStringToListInt(String usersId) throws SQLException, ClassNotFoundException {
        ProfileDao profileDao = new ProfileDaoImpl();
        String[] users = usersId.split(";").clone();
        List<Profile> result = new ArrayList<>();
        for (String user: users) {
            result.add(profileDao.getById(Integer.parseInt(user)));
        }
        return result;
    }

    public static String parseStringToListInt(List<Profile> usersId){
        StringBuilder result = new StringBuilder();
        for (Profile user : usersId) {
            result.append(user.getId()).append(";");
        }
        return result.toString();
    }

    public static boolean parseStringToBoolean(String isPrivate){
        if (isPrivate == "true"){
            return true;
        }else if (isPrivate == "false"){
            return false;
        }else {
            return false;
        }
    }

    public static String parseBooleanToString(boolean isPrivate){
        if (isPrivate){
            return "true";
        }else {
            return "false";
        }
    }
}
