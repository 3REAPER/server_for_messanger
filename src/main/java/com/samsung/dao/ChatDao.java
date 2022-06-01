package com.samsung.dao;

import com.samsung.domain.Chat;
import com.samsung.domain.Message;

import java.util.List;

public interface ChatDao {
    void insert(Chat chat);

    void update(Chat chat);

    void delete(int id);

    Chat getById(int id);

    List<Chat> getByName(String name);

    List<Chat> getAllByUserId(int id);
}
