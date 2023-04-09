package com.pervukhin.domain;

import com.pervukhin.dao.ConditionSendDao;
import com.pervukhin.dao.ConditionSendDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Message {
    private int id;
    private String time;
    private Profile authorId;
    private int chatId;
    private List<ConditionSend> conditionSend;
    private boolean isPhoto;

    public Message(int id, String time, Profile authorId, int chatId, String conditionSend, boolean isPhoto) {
        this.id = id;
        this.time = time;
        this.authorId = authorId;
        this.chatId = chatId;
        this.conditionSend = parseStringToList(conditionSend);
        this.isPhoto = isPhoto;
    }

    public Message(String time, Profile authorId, int chatId, String conditionSend, boolean isPhoto) {
        this.time = time;
        this.authorId = authorId;
        this.chatId = chatId;
        this.conditionSend = parseStringToList(conditionSend);
        this.isPhoto = isPhoto;
    }

    public Message(int id, String time, Profile authorId, int chatId, List<ConditionSend> conditionSend, boolean isPhoto) {
        this.id = id;
        this.time = time;
        this.authorId = authorId;
        this.chatId = chatId;
        this.conditionSend = conditionSend;
        this.isPhoto = isPhoto;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public Profile getAuthor() {
        return authorId;
    }

    public int getChatId() {
        return chatId;
    }

    public List<ConditionSend> getConditionSend() {
        return conditionSend;
    }

    public boolean getIsPhoto() {
        return isPhoto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAuthorId(Profile authorId) {
        this.authorId = authorId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setConditionSend(List<ConditionSend> conditionSend) {
        this.conditionSend = conditionSend;
    }

    public void setPhoto(boolean photo) {
        isPhoto = photo;
    }

    public List<ConditionSend> parseStringToList(String conditionSend){
        try {
            ConditionSendDao conditionSendDao = new ConditionSendDaoImpl();
            String[] messages = conditionSend.split(";").clone();
            List<ConditionSend> result = new ArrayList<>();
            for (String message : messages) {
                if (!Objects.equals(message, "")) {
                    result.add(conditionSendDao.getById(Integer.parseInt(message)));
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String parseListToString(List<ConditionSend> messages) {
        try {
            StringBuilder result = new StringBuilder();
            for (ConditionSend message : messages) {
                result.append(message.getId()).append(";");
            }
            return result.toString();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean parseStringToBoolean(String string){
        if (string.equals("true")){
            return true;
        }else if (string.equals("false")){
            return false;
        }else {
            return null;
        }
    }

}
