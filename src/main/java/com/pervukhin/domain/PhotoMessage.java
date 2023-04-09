package com.pervukhin.domain;

import java.util.List;

public class PhotoMessage extends Message {
    private String path;


    public PhotoMessage(int id, String time, Profile authorId, int chatId, String conditionSend, boolean isPhoto, String path) {
        super(id, time, authorId, chatId,conditionSend,isPhoto);
        this.path = path;
    }

    public PhotoMessage(String time, Profile authorId, int chatId, String conditionSend, boolean isPhoto, String path) {
        super(time, authorId, chatId, conditionSend, isPhoto);
        this.path = path;
    }

    public PhotoMessage(int id,String time, Profile authorId, int chatId, List<ConditionSend> conditionSend, boolean isPhoto, String path) {
        super(id, time, authorId, chatId, conditionSend, isPhoto);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
