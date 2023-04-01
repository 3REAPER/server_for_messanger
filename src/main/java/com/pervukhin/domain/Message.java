package com.pervukhin.domain;

import com.pervukhin.dao.ConditionSendDao;
import com.pervukhin.dao.ConditionSendDaoImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Message {
    private int id;
    private String message;
    private String time;
    private String isEdit;
    private Profile authorId;
    private List<ConditionSend> conditionSend;
    private int chatId;

    public Message(int id, String message, String time, String isEdit, Profile authorId, String conditionSend, int chatId) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.authorId = authorId;
        this.conditionSend = parseStringToList(conditionSend);
        this.chatId = chatId;
    }

    public Message(String message, String time, String isEdit, Profile authorId, String conditionSend, int chatId) {
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.authorId = authorId;
        this.conditionSend = parseStringToList(conditionSend);
        this.chatId = chatId;
    }

    public Message(int id, String message, String time, String isEdit, Profile authorId, List<ConditionSend> conditionSend, int chatId) {
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.authorId = authorId;
        this.conditionSend = conditionSend;
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public void setAuthorId(Profile authorId) {
        this.authorId = authorId;
    }

    public void setConditionSend(List<ConditionSend> conditionSend) {
        this.conditionSend = conditionSend;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public Profile getAuthor() {
        return authorId;
    }

    public List<ConditionSend> getConditionSend() {
        return conditionSend;
    }

    public int getChatId() {
        return chatId;
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

    public static String getDateNow(){
        String[] arrayListDate = LocalDate.now().toString().split("-");
        String[] arrayListTime = LocalTime.now().toString().split(":");
        return arrayListDate[2] +"-" +arrayListDate[1] +"-" +arrayListDate[0] +"T" +arrayListTime[0] +":" +arrayListTime[1];
    }
}
