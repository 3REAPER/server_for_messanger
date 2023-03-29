package com.pervukhin.service;

import com.pervukhin.domain.Chat;

import java.sql.SQLException;
import java.util.List;

public interface ChatService {

    void insert(Chat chat);

    void update(Chat chat);

    void delete(int id);

    Chat getById(int id);

    List<Chat> getByName(String name);

    List<Chat> getAllByUserId(int id);

    void addUser(int chatId,int userId);

    Chat getByUsers(int myId, int userId);
}
