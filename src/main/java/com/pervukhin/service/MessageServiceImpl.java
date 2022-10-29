package com.pervukhin.service;

import antlr.debug.MessageAdapter;
import com.pervukhin.dao.ChatDao;
import com.pervukhin.dao.ChatDaoImpl;
import com.pervukhin.dao.MessageDao;
import com.pervukhin.dao.MessageDaoImpl;
import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService{
    private MessageDao messageDao;

    private ChatDao chatDao;

    public MessageServiceImpl() throws SQLException, ClassNotFoundException {
        this.messageDao = new MessageDaoImpl();
        this.chatDao = new ChatDaoImpl();
    }

    @Override
    public void insert(Message message, int chatId) {
        messageDao.insert(message, chatId);
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
    public List<Message> getAllByChatId(int chatId) throws SQLException, ClassNotFoundException {
        return messageDao.getAllByChatId(chatId);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }
}
