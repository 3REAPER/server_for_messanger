package com.pervukhin.domain;

import com.pervukhin.dao.ConditionSendDao;
import com.pervukhin.dao.ConditionSendDaoImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextMessage extends Message {
    private String message;
    private String isEdit;

    public TextMessage(int id, String message, String time, String isEdit, Profile authorId, String conditionSend, int chatId, boolean isPhoto) {
        super(id,time,authorId,chatId, conditionSend, isPhoto);
        this.message = message;
        this.isEdit = isEdit;
    }

    public TextMessage(String message, String time, String isEdit, Profile authorId, String conditionSend, int chatId, boolean isPhoto) {
        super(time,authorId,chatId, conditionSend, isPhoto);
        this.message = message;
        this.isEdit = isEdit;
    }

    public TextMessage(int id, String message, String time, String isEdit, Profile authorId, List<ConditionSend> conditionSend, int chatId, boolean isPhoto) {
        super(id,time,authorId,chatId, conditionSend, isPhoto);
        this.message = message;
        this.isEdit = isEdit;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getMessage() {
        return message;
    }

    public String getIsEdit() {
        return isEdit;
    }
    
    public static String getDateNow(){
        String[] arrayListDate = LocalDate.now().toString().split("-");
        String[] arrayListTime = LocalTime.now().toString().split(":");
        return arrayListDate[2] +"-" +arrayListDate[1] +"-" +arrayListDate[0] +"T" +arrayListTime[0] +":" +arrayListTime[1];
    }
}
