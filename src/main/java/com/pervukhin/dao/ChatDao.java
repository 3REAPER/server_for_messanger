package com.pervukhin.dao;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Message;

import java.sql.SQLException;
import java.util.List;

public interface ChatDao {
    void insert(Chat chat);

    void update(Chat chat);

    void delete(int id);

    Chat getById(int id);

    List<Chat> getByName(String name);

    List<Chat> getAllByUserId(int id);
    List<Chat> getAll();
}
