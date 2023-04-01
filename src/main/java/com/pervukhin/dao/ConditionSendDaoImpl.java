package com.pervukhin.dao;

import com.pervukhin.LibraryApp;
import com.pervukhin.domain.Chat;
import com.pervukhin.domain.ConditionSend;
import com.pervukhin.domain.Message;
import com.pervukhin.domain.Profile;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConditionSendDaoImpl implements ConditionSendDao{
    private static final String CON_STR = "jdbc:sqlite:C:Users/Nikita/Desktop/database.db";
    private static ChatDao instance = null;
    private final Connection connection;

    public ConditionSendDaoImpl() throws SQLException, ClassNotFoundException {
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
    public void insert(ConditionSend conditionSend) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO ConditionSend(`profile`, `condition`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, conditionSend.getProfile().getId());
            statement.setObject(2, conditionSend.getCondition());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> insert(List<ConditionSend> list) {
        List<Integer> result = new ArrayList<>();
        for (ConditionSend conditionSend: list) {
            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO ConditionSend(`profile`, `condition`) " +
                            "VALUES(?, ?)")) {
                statement.setObject(1, conditionSend.getProfile().getId());
                statement.setObject(2, conditionSend.getCondition());
                statement.execute();
                result.add(statement.getGeneratedKeys().getInt("last_insert_rowId()"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void update(ConditionSend conditionSend) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE ConditionSend SET profile= ?, condition=? WHERE id = ?")) {
            statement.setObject(1, conditionSend.getProfile().getId());
            statement.setObject(2, conditionSend.getCondition());
            statement.setObject(3, conditionSend.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ConditionSend getById(int id) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM ConditionSend WHERE id = ?")) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new ConditionSend(
                    resultSet.getInt("id"),
                    resultSet.getInt("profile"),
                    resultSet.getInt("condition")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM ConditionSend WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
