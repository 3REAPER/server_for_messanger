package com.samsung.service;

import com.samsung.domain.Chat;

import java.util.List;

public interface ChatService {

    void insert(Chat chat);

    void update(Chat chat);

    void delete(int id);

    Chat getById(int id);

    List<Chat> getByName(String name);

    List<Chat> getAllByUserId(int id);

    void addUser(int chatId,int userId);

    boolean isHasProfile(Chat chat, int profileId);
}
