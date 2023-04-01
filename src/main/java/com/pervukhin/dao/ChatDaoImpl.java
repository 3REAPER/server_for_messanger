package com.pervukhin.dao;

import com.pervukhin.LibraryApp;
import com.pervukhin.domain.Chat;
import com.pervukhin.domain.GroupChat;
import com.pervukhin.domain.Message;
import com.pervukhin.domain.Profile;
import org.sqlite.JDBC;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatDaoImpl implements ChatDao{
    private static final String CON_STR = "jdbc:sqlite:C:Users/Nikita/Desktop/database.db";
    private static ChatDao instance = null;
    private final Connection connection;

    public ChatDaoImpl() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new JDBC());
        Class.forName("org.sqlite.JDBC");
        this.connection = LibraryApp.connection;
    }

    public static synchronized ChatDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new ChatDaoImpl();
        return instance;
    }

    @Override
    public void insert(Chat chat) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Chat(`name`, `description`, `admin`, `usersId`, `isPrivate`, `messages`, `isGroup`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            if (chat instanceof GroupChat) {
                GroupChat groupChat = ((GroupChat) chat);
                statement.setObject(1, groupChat.getName());
                statement.setObject(2, groupChat.getDescription());
                statement.setObject(3, groupChat.getAdmin().getId());
                statement.setObject(4, Chat.parseListIntToString(groupChat.getUsersId()));
                statement.setObject(5, groupChat.getIsPrivate());
                if (groupChat.getMessages().isEmpty()) {
                    statement.setObject(6, "");
                } else statement.setObject(6, groupChat.getMessages());

            }else {
                statement.setObject(1, null);
                statement.setObject(2, null);
                statement.setObject(3, null);
                statement.setObject(4, Chat.parseListIntToString(chat.getUsersId()));
                statement.setObject(5, null);
                if (chat.getMessages().isEmpty()) {
                    statement.setObject(6, "");
                } else statement.setObject(6, chat.getMessages());
                statement.setObject(7,chat.getIsGroup());
            }

            statement.execute();
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Chat chat) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Chat SET name=?, description=?, admin=?,usersId=?, isPrivate=?, messages=?, isGroup=? WHERE id = ?")) {
            if (chat instanceof GroupChat) {
                GroupChat groupChat = ((GroupChat) chat);
                statement.setObject(1, groupChat.getName());
                statement.setObject(2, groupChat.getDescription());
                statement.setObject(3, groupChat.getAdmin().getId());
                statement.setObject(4, Chat.parseListIntToString(chat.getUsersId()));
                statement.setObject(5, groupChat.getIsPrivate());
                statement.setObject(6, Chat.parseListMessagesToString(chat.getMessages()));
                statement.setObject(7, groupChat.getId());
            }else {
                statement.setObject(1, null);
                statement.setObject(2, null);
                statement.setObject(3, null);
                statement.setObject(4, Chat.parseListIntToString(chat.getUsersId()));
                statement.setObject(5, null);
                statement.setObject(6, Chat.parseListMessagesToString(chat.getMessages()));
                statement.setObject(7, chat.getIsGroup().toString());
                statement.setObject(8, chat.getId());
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Chat WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chat getById(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Chat WHERE id= ?")) {
            statement.setObject(1,id);
            ResultSet resultSet = statement.executeQuery();
            return getChatByResultSet(resultSet);
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Chat> getByName(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Chat WHERE name = ?")) {
            statement.setObject(1,name);
            ResultSet resultSet = statement.executeQuery();
            List<Chat> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getChatByResultSet(resultSet));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Chat> getAllByUserId(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Chat WHERE usersId LIKE ?")) {
            statement.setObject(1,"%" +id +"%");
            ResultSet resultSet = statement.executeQuery();
            List<Chat> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getChatByResultSet(resultSet));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Chat> getAll() {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Chat")) {
            ResultSet resultSet = statement.executeQuery();
            List<Chat> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getChatByResultSet(resultSet));
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Chat getChatByResultSet(ResultSet resultSet){
        try {
            if (resultSet.getString("isGroup").equals("true")) {
                return new GroupChat(
                        resultSet.getInt("id"),
                        resultSet.getString("usersId"),
                        resultSet.getString("messages"),
                        resultSet.getString("isGroup"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("isPrivate"),
                        resultSet.getInt("admin")
                );
            }else {
                return new Chat(
                        resultSet.getInt("id"),
                        resultSet.getString("usersId"),
                        resultSet.getString("messages"),
                        resultSet.getString("isGroup")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
