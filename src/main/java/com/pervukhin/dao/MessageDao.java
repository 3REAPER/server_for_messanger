package com.pervukhin.dao;

import com.pervukhin.domain.Message;

import java.util.List;

public interface MessageDao {
    void insert(Message message, int chatId);

    void update(Message message);

    void delete(int id);

    Message getById(int id);

    List<Message> getAllByChatId(int chatId);

    List<Message> getAll();
}
