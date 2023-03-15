package com.pervukhin.service;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {

    void insert(Message message, int chatId);

    void update(Message message);

    void delete(int id);

    Message getById(int id);

    List<Message> getAllByChatId(int chatId) throws SQLException, ClassNotFoundException;

    List<Message> getAll();
    List<Message> getUnread(int profileId);
}
