package com.samsung.dao;

import com.samsung.domain.Message;

import java.util.List;

public interface ChatDao {
    void insert(Message message);

    void update(Message message);

    void delete(int id);

    Message getById(int id);

    List<Message> getByName(String name);

    List<Message> getAll(String login);

    List<Message> addUser(int userId);
}
