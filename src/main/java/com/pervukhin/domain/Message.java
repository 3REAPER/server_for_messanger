package com.pervukhin.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    private int id;
    private String message;
    private String time;
    private String isEdit;
    private Profile authorId;
    private int conditionSend;
    private int chatId;

    public Message(int id, String message, String time, String isEdit, Profile authorId, int conditionSend, int chatId) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.authorId = authorId;
        this.conditionSend = conditionSend;
        this.chatId = chatId;
    }

    public Message(String message, String time, String isEdit, Profile authorId, int conditionSend, int chatId) {
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

    public void setConditionSend(int conditionSend) {
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

    public int getConditionSend() {
        return conditionSend;
    }

    public int getChatId() {
        return chatId;
    }

    public static String getDateNow(){
        String[] arrayListDate = LocalDate.now().toString().split("-");
        String[] arrayListTime = LocalTime.now().toString().split(":");
        return arrayListDate[2] +"-" +arrayListDate[1] +"-" +arrayListDate[0] +"T" +arrayListTime[0] +":" +arrayListTime[1];
    }
}
