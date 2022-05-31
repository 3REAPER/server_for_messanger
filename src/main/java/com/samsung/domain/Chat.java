package com.samsung.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat {
    private int id;
    private String name;
    private List<Integer> usersId;

    public Chat(int id, String name,String usersId) {
        this.id = id;
        this.name = name;
        this.usersId = parseStringToListInt(usersId);
    }

    public Chat(String name, String usersId) {
        this.name = name;
        this.usersId = parseStringToListInt(usersId);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }

    public List<Integer> parseStringToListInt(String usersId){
        String[] users = usersId.split(";").clone();
        List<Integer> result = new ArrayList<>();
        for (String user: users) {
            result.add(Integer.parseInt(user));
        }
        return result;
    }

    public String parseStringToListInt(List<Integer> usersId){
        StringBuilder result = new StringBuilder();
        for (int userId : usersId) {
            result.append(userId).append(";");
        }
        return result.toString();
    }
}
