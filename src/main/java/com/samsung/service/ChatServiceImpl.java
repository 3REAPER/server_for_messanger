package com.samsung.service;

import com.samsung.dao.ChatDao;
import com.samsung.dao.ChatDaoImpl;
import com.samsung.dao.ProfileDao;
import com.samsung.dao.ProfileDaoImpl;
import com.samsung.domain.Chat;
import com.samsung.domain.Profile;

import java.sql.SQLException;
import java.util.List;

public class ChatServiceImpl implements ChatService{
    private final ChatDao chatDao;
    private final ProfileDao profileDao;

    public ChatServiceImpl() throws SQLException, ClassNotFoundException {
        this.chatDao = new ChatDaoImpl();
        this.profileDao = new ProfileDaoImpl();
    }

    @Override
    public void insert(Chat chat) {
        chatDao.insert(chat);
    }

    @Override
    public void update(Chat chat) {
        chatDao.update(chat);
    }

    @Override
    public void delete(int id) {
        chatDao.delete(id);
    }

    @Override
    public Chat getById(int id) {
        return chatDao.getById(id);
    }

    @Override
    public List<Chat> getByName(String name) {
        return chatDao.getByName(name);
    }

    @Override
    public List<Chat> getAllByUserId(int id) {
        return chatDao.getAllByUserId(id);
    }

    @Override
    public void addUser(int chatId, int userId) {
        Chat chat = getById(chatId);
        Profile profile = profileDao.getById(userId);
        if (profile != null && !isHasProfile(chat,profile.getId())) {
            try {
                List<Profile> list = chat.getUsersId();
                list.add(profile);
                chat.setUsersId(list);
                update(chat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isHasProfile(Chat chat, int profileId) {
        for (Profile profile: chat.getUsersId()) {
            if (profile.getId() == profileId){
                return true;
            }
        }
        return false;
    }
}