package com.samsung.dao;

import com.samsung.domain.Message;
import com.samsung.domain.Profile;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao{
    private static final String CON_STR = "jdbc:sqlite:C:/Users/Nikita/Desktop/project/database/database.db";
    private static MessageDaoImpl instance = null;
    private final ProfileDao profileDao;
    private final Connection connection;

    public MessageDaoImpl() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(CON_STR);
        this.profileDao = new ProfileDaoImpl();
    }

    public static synchronized MessageDaoImpl getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new MessageDaoImpl();
        return instance;
    }

    @Override
    public void insert(Message message) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Message(`message`, `time`, `isEdit`, `chatId`, `authorId`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, message.getMessage());
            statement.setObject(2, message.getTime());
            statement.setObject(3, message.getIsEdit());
            statement.setObject(4, message.getChatId());
            statement.setObject(5, message.getAuthorId());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Message SET message=?, time=?, isEdit=?, chatId=?, authorId=? WHERE id = ?")) {
            statement.setObject(1, message.getMessage());
            statement.setObject(2, message.getTime());
            statement.setObject(3, message.getIsEdit());
            statement.setObject(4, message.getChatId());
            statement.setObject(5, message.getAuthorId());
            statement.setObject(6, message.getId());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Message WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message getById(int id) {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Message WHERE id="+id);
            return new Message(
                    resultSet.getInt("id"),
                    resultSet.getString("message"),
                    resultSet.getString("time"),
                    resultSet.getInt("isEdit"),
                    resultSet.getInt("chatId"),
                    profileDao.getById(resultSet.getInt("authorId"))
            );
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getAllByChatId(int chatId) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Message WHERE chatId = ?")) {
            statement.setObject(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            List<Message> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message"),
                        resultSet.getString("time"),
                        resultSet.getInt("isEdit"),
                        resultSet.getInt("chatId"),
                        profileDao.getById(resultSet.getInt("authorId"))
                ));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getAll() {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Message")) {
            ResultSet resultSet = statement.executeQuery();
            List<Message> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("message"),
                        resultSet.getString("time"),
                        resultSet.getInt("isEdit"),
                        resultSet.getInt("chatId"),
                        profileDao.getById(resultSet.getInt("authorId"))
                ));
            }
            return list;
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
