package com.pervukhin.service;

import com.pervukhin.domain.Message;
import com.pervukhin.domain.PhotoMessage;
import com.pervukhin.domain.TextMessage;

import java.util.List;

public interface MessageService {

    void insert(Message message);

    void update(Message message);

    void delete(int id);

    Message getById(int id);

    List<Message> getAllByChatId(int chatId);

    List<Message> getAll();

    List<Message> getUnread(int profileId);
}
