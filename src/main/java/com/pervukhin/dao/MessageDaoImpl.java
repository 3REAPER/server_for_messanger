package com.pervukhin.dao;

import com.pervukhin.domain.Chat;
import com.pervukhin.domain.Message;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao{
    private static final String CON_STR = "jdbc:sqlite:C:Users/Nikita/Desktop/database.db";
    private static MessageDaoImpl instance = null;
    private final ProfileDao profileDao;
    private final ChatDao chatDao;
    private final Connection connection;

    public MessageDaoImpl() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(CON_STR);
        this.profileDao = new ProfileDaoImpl();
        this.chatDao = new ChatDaoImpl();
    }

    public static synchronized MessageDaoImpl getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new MessageDaoImpl();
        return instance;
    }

    @Override
    public void insert(Message message, int chatId) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Message(`message`, `time`, `isEdit`, `authorId`, `conditionSend`, `chatId`) " +
                        "VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, message.getMessage());
            statement.setObject(2, message.getTime());
            statement.setObject(3, message.getIsEdit());
            statement.setObject(4, message.getAuthor().getId());
            statement.setObject(5, message.getConditionSend());
            statement.setObject(6, message.getChatId());
            // Выполняем запрос

            statement.execute();
            int messageId = statement.getGeneratedKeys().getInt("last_insert_rowId()");
            Chat chat = chatDao.getById(chatId);
            String oldMessages = Chat.parseListMessagesToString(chat.getMessages());
            String newMessages = "";
            if (oldMessages.equals("")){
                newMessages = String.valueOf(messageId);
            }else {
                newMessages = oldMessages +";" +messageId;
            }
            chat.setMessages(newMessages);
            chatDao.update(chat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Message SET message=?, time=?, isEdit=?, authorId=?, conditionSend=?, chatId=? WHERE id = ?")) {
            statement.setObject(1, message.getMessage());
            statement.setObject(2, message.getTime());
            statement.setObject(3, message.getIsEdit());
            statement.setObject(4, message.getAuthor().getId());
            statement.setObject(5, message.getConditionSend());
            statement.setObject(6, message.getChatId());
            statement.setObject(7, message.getId());
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
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Message WHERE id = ?")) {
            statement.setObject(1,id);
            ResultSet resultSet = statement.executeQuery();
            return new Message(
                    resultSet.getInt("id"),
                    resultSet.getString("message"),
                    resultSet.getString("time"),
                    resultSet.getString("isEdit"),
                    profileDao.getById(resultSet.getInt("authorId")),
                    resultSet.getInt("conditionSend"),
                    resultSet.getInt("chatId")
            );
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getAllByChatId(int chatId) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Chat WHERE id = ?")) {
            statement.setObject(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            return Chat.parseStringToListMessages(resultSet.getString("messages"));
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
                        resultSet.getString("isEdit"),
                        profileDao.getById(resultSet.getInt("authorId")),
                        resultSet.getInt("conditionSend"),
                        resultSet.getInt("chatId")
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
