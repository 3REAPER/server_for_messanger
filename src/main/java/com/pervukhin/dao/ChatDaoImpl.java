package com.pervukhin.dao;

import com.pervukhin.domain.Chat;
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
        // Выполняем подключение к базе данных
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public static synchronized ChatDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new ChatDaoImpl();
        return instance;
    }

    @Override
    public void insert(Chat chat) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Chat(`name`, `description`, `admin`, `usersId`, `isPrivate`, `messages`) " +
                        "VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, chat.getName());
            statement.setObject(2, chat.getDescription());
            statement.setObject(3, chat.getAdmin().getId());
            statement.setObject(4, Chat.parseListIntToString(chat.getUsersId()));
            statement.setObject(5, chat.getIsPrivate());
            statement.setObject(6, chat.getMessages());
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Chat chat) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Chat SET name=?, description=?, admin=?,usersId=?, isPrivate=?, messages=? WHERE id = ?")) {
            statement.setObject(1, chat.getName());
            statement.setObject(2, chat.getDescription());
            statement.setObject(3, chat.getAdmin().getId());
            statement.setObject(4, Chat.parseListIntToString(chat.getUsersId()));
            statement.setObject(5, chat.getIsPrivate());
            statement.setObject(6, Chat.parseListMessagesToString(chat.getMessages()));
            statement.setObject(7, chat.getId());

            // Выполняем запрос
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
            return new Chat(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("admin"),
                    resultSet.getString("usersId"),
                    resultSet.getString("isPrivate"),
                    resultSet.getString("messages")
            );
            // Выполняем запрос
        } catch (SQLException | ClassNotFoundException e) {
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
                list.add(new Chat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("admin"),
                        resultSet.getString("usersId"),
                        resultSet.getString("isPrivate"),
                        resultSet.getString("messages")
                ));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException | ClassNotFoundException e) {
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
                list.add(new Chat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("admin"),
                        resultSet.getString("usersId"),
                        resultSet.getString("isPrivate"),
                        resultSet.getString("messages")
                ));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException | ClassNotFoundException e) {
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
                list.add(new Chat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("admin"),
                        resultSet.getString("usersId"),
                        resultSet.getString("isPrivate"),
                        resultSet.getString("messages")
                ));
            }
            return list;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
