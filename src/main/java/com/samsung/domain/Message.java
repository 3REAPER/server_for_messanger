package com.samsung.domain;

public class Message {
    private int id;
    private String message;
    private String time;
    private int isEdit;
    private int chatId;

    public Message(int id, String message, String time, int isEdit, int chatId) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.chatId = chatId;
    }

    public Message(String message, String time, int isEdit,int chatId) {
        this.message = message;
        this.time = time;
        this.isEdit = isEdit;
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIsEdit(int isEdit) {
        this.isEdit = isEdit;
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

    public int getIsEdit() {
        return isEdit;
    }

    public int getChatId() {
        return chatId;
    }
}
