package com.pervukhin.service;

import com.pervukhin.dao.*;
import com.pervukhin.domain.Chat;
import com.pervukhin.domain.ConditionSend;
import com.pervukhin.domain.Message;
import com.pervukhin.domain.Profile;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private final ChatDao chatDao;
    private final ConditionSendDao conditionSendDao;

    public MessageServiceImpl() throws SQLException, ClassNotFoundException {
        this.messageDao = new MessageDaoImpl();
        this.chatDao = new ChatDaoImpl();
        this.conditionSendDao = new ConditionSendDaoImpl();
    }

    @Override
    public void insert(Message message, int chatId) {
        List<ConditionSend> conditionSends = new ArrayList<>();
        for (Profile profile : chatDao.getById(chatId).getUsersId()) {
            if (profile.getId() != message.getAuthor().getId()) {
                conditionSends.add(new ConditionSend(profile.getId(), ConditionSend.CONDITION_CREATE));
            }
        }
        List<Integer> listId = conditionSendDao.insert(conditionSends);
        List<ConditionSend> conditionSendsWithId = new ArrayList<>();
        for (int id : listId) {
                conditionSendsWithId.add(conditionSendDao.getById(id));
        }
        message.setConditionSend(conditionSendsWithId);
        messageDao.insert(message, chatId);
    }

    @Override
    public void update(Message message) {
        messageDao.update(message);
        for (ConditionSend conditionSend: message.getConditionSend()){
            conditionSendDao.update(conditionSend);
        }
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
    public List<Message> getAllByChatId(int chatId){
        return messageDao.getAllByChatId(chatId);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }

    @Override
    public List<Message> getUnread(int profileId) {
        List<Chat> chats = chatDao.getAllByUserId(profileId);
        List<Message> unreadMessages = new ArrayList<>();
        for (Chat chat : chats) {
            for (Message message : chat.getMessages()) {
                List<ConditionSend> list = message.getConditionSend();
                for (ConditionSend conditionSend: list) {
                    if (conditionSend.getProfile().getId() == profileId && conditionSend.getCondition() == ConditionSend.CONDITION_CREATE){
                        unreadMessages.add(message);
                    }
                }
            }
        }
        return unreadMessages;
    }
}
