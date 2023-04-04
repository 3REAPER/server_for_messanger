package com.pervukhin.domain;

import com.pervukhin.dao.MessageDao;
import com.pervukhin.dao.MessageDaoImpl;
import com.pervukhin.dao.ProfileDao;
import com.pervukhin.dao.ProfileDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chat {
    private int id;
    private List<Profile> usersId;
    private List<Message> messages;
    private Boolean isGroup;

    public Chat(int id,  String usersId, String messages, String isGroup){
        this.id = id;
        this.usersId = parseStringToListInt(usersId);
        this.messages = parseStringToListMessages(messages);
        this.isGroup = parseStringToBoolean(isGroup);

    }
    
    public Chat(String usersId, String messages, String isGroup){
        this.usersId = parseStringToListInt(usersId);
        this.messages = parseStringToListMessages(messages);
        this.isGroup = parseStringToBoolean(isGroup);
    }

    public Chat(int id, List<Profile> userList, List<Message> messageList, Boolean isGroup) {
        this.id = id;
        this.usersId = userList;
        this.messages = messageList;
        this.isGroup = isGroup;
    }

    public int getId() {
        return id;
    }

    public List<Profile> getUsersId() {
        return usersId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setUsersId(List<Profile> usersId) {
        this.usersId = usersId;

    }

    public void setUsersId(String usersId){
        this.usersId = parseStringToListInt(usersId);
    }
    public void setMessages(String messages) {
        this.messages = parseStringToListMessages(messages);
    }

    public void addMessages(Message messages) {
        this.messages.add(messages);
    }

    public void setIsGroup(Boolean group) {
        isGroup = group;
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
    public static boolean parseStringToBoolean(String string){
        if (string.equals("true")){
            return true;
        }else if (string.equals("false")){
            return false;
        }else {
            return false;
        }
    }
}
