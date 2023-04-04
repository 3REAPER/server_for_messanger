package com.pervukhin.dao;

import com.pervukhin.domain.Chat;
import java.util.List;

public interface ChatDao {
    void insert(Chat chat);

    void update(Chat chat);

    void delete(int id);

    Chat getById(int id);

    List<Chat> getByName(String name);

    List<Chat> getAllByUserId(int id);
    List<Chat> getAll();

    List<Chat> getAllByNameNoPrivate(String name);
}
