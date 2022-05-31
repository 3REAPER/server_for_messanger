package com.samsung.service;

import com.samsung.dao.MessageDao;
import com.samsung.dao.MessageDaoImpl;
import com.samsung.dao.ProfileDao;
import com.samsung.dao.ProfileDaoImpl;
import com.samsung.domain.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{
    private MessageDao messageDao;

    public MessageServiceImpl() throws SQLException, ClassNotFoundException {
        this.messageDao = new MessageDaoImpl();
    }

    @Override
    public void insert(Message message) {
        messageDao.insert(message);
    }

    @Override
    public void update(Message message) {
        messageDao.update(message);
    }

    @Override
    public void delete(int id) {
        messageDao.delete(id);
    }

    @Override
    public Message getById(int id) {
        return messageDao.getById(id);
    }

    @Override
    public List<Message> getAllByChatId(int chatId) {
        return messageDao.getAllByChatId(chatId);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }
}
