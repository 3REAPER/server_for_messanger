package com.pervukhin.domain;

import com.pervukhin.dao.MessageDao;
import com.pervukhin.dao.MessageDaoImpl;
import com.pervukhin.dao.ProfileDao;
import com.pervukhin.dao.ProfileDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chat {
    private int id;
    private String name;
    private String description;
    private String isPrivate;
    private Profile admin;
    private List<Profile> usersId;
    private List<Message> messages;

    public Chat(int id, String name, String description, int adminId, String usersId, String isPrivate, String messages) throws SQLException, ClassNotFoundException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.admin = parseIntToAdmin(adminId);
        this.usersId = parseStringToListInt(usersId);
        this.isPrivate = isPrivate;
        this.messages = parseStringToListMessages(messages);

    }
    
    public Chat(String name, String description,Profile admin ,String usersId, String isPrivate, String messages) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.description = description;
        this.admin = admin;
        this.usersId = parseStringToListInt(usersId);
        this.isPrivate = isPrivate;
        this.messages = parseStringToListMessages(messages);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Profile getAdmin() {
        return admin;
    }

    public List<Profile> getUsersId() {
        return usersId;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdmin(int adminId) {
        this.admin = parseIntToAdmin(adminId);
    }

    public void setUsersId(List<Profile> usersId) {
        this.usersId = usersId;

    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setUsersId(String usersId){
        this.usersId = parseStringToListInt(usersId);
    }
    public void setMessages(String messages) {
        this.messages = parseStringToListMessages(messages);
    }
    
    public static Profile parseIntToAdmin(int adminId){
        try {
            ProfileDao profileDao = new ProfileDaoImpl();
            return profileDao.getById(adminId);
        }catch (Exception e){
            return null;
        }
    }

    public static List<Profile> parseStringToListInt(String usersId) {
        try {
            ProfileDao profileDao = new ProfileDaoImpl();
            String[] users = usersId.split(";").clone();
            List<Profile> result = new ArrayList<>();
            for (String user : users) {
                result.add(profileDao.getById(Integer.parseInt(user)));
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String parseListIntToString(List<Profile> usersId){
        StringBuilder result = new StringBuilder();
        for (Profile user : usersId) {
            result.append(user.getId()).append(";");
        }
        return result.toString();
    }

    public static List<Message> parseStringToListMessages(String messagesId){
        try {
            MessageDao messageDao = new MessageDaoImpl();
            String[] messages = messagesId.split(";").clone();
            List<Message> result = new ArrayList<>();
            for (String message : messages) {
                if (!Objects.equals(message, "")) {
                    result.add(messageDao.getById(Integer.parseInt(message)));
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String parseListMessagesToString(List<Message> messages) {
        try {
            StringBuilder result = new StringBuilder();
            for (Message message : messages) {
                result.append(message.getId()).append(";");
            }
            return result.toString();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
