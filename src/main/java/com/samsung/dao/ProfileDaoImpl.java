package com.samsung.dao;

import com.samsung.domain.Profile;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDaoImpl implements ProfileDao{
    private static final String CON_STR = "jdbc:sqlite:C:/Users/Nikita/Desktop/project/database/database.db";
    private static ProfileDaoImpl instance = null;
    private Connection connection;

    public ProfileDaoImpl() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public static synchronized ProfileDaoImpl getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new ProfileDaoImpl();
        return instance;
    }

    @Override
    public void insert(Profile profile) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Profile(`name`, `login`, `password`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, profile.getName());
            statement.setObject(2, profile.getLogin());
            statement.setObject(3, profile.getPassword());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Profile profile) {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Profile SET name= ?, login=?, password=? WHERE id = ?")) {
            statement.setObject(1, profile.getName());
            statement.setObject(2, profile.getLogin());
            statement.setObject(3, profile.getPassword());
            statement.setObject(4, profile.getId());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Profile WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile getById(int id) {
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Profile WHERE id="+id);
            return new Profile(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password")
            );
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Profile> getByName(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Profile WHERE name = ?")) {
            statement.setObject(1,name);
            ResultSet resultSet = statement.executeQuery();
            List<Profile> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Profile(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
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
    public List<Profile> getByLogin(String login) {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Profile WHERE login = ?")) {
            statement.setObject(1, login);
            ResultSet resultSet = statement.executeQuery();
            List<Profile> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Profile(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
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
