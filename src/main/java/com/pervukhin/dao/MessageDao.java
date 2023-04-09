package com.pervukhin.dao;

import com.pervukhin.domain.Message;
import com.pervukhin.domain.PhotoMessage;
import com.pervukhin.domain.TextMessage;

import java.util.List;

public interface MessageDao {
    void insert(TextMessage message);
    void insert(PhotoMessage message);

    void update(TextMessage message);
    void update(PhotoMessage message);

    void delete(int id);

    Message getById(int id);

    List<Message> getAllByChatId(int chatId);

    List<Message> getAll();
}
