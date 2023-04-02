package com.pervukhin.domain;

import java.sql.SQLException;
import java.util.List;

public class GroupChat extends Chat{
    private String name;
    private String description;
    private Boolean isPrivate;
    private Profile admin;

    public GroupChat(int id, String usersId, String messages, String isGroup, String name, String description, String isPrivate, int admin) throws SQLException, ClassNotFoundException {
        super(id, usersId, messages, isGroup);
        this.name = name;
        this.description = description;
        this.isPrivate = parseStringToBoolean(isPrivate);
        this.admin = parseIntToAdmin(admin);
    }

    public GroupChat(String usersId, String messages, String isGroup, String name, String description, String isPrivate, int admin) throws SQLException, ClassNotFoundException {
        super(usersId, messages, isGroup);
        this.name = name;
        this.description = description;
        this.isPrivate = parseStringToBoolean(isPrivate);
        this.admin = parseIntToAdmin(admin);
    }

    public GroupChat(int id, List<Profile> userList, List<Message> messageList, Boolean isGroup, String name, String description, Boolean isPrivate, Profile admin) {
        super(id, userList, messageList, isGroup);
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public Profile getAdmin() {
        return admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setAdmin(Profile admin) {
        this.admin = admin;
    }
}
