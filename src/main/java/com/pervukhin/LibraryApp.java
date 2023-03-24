package com.pervukhin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// url h2 консоли: http://localhost:8080/h2-console
// url базы: jdbc:h2:mem:testdb

@SpringBootApplication
public class LibraryApp {
    private static final String CON_STR = "jdbc:sqlite:C:Users/Nikita/Desktop/database.db";
    public static Connection connection;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Запуск SpringBoot-приложения и получение контекста
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(CON_STR);
        ConfigurableApplicationContext context = SpringApplication.run(LibraryApp.class, args);
        /*try {
            //Консоль для визуализации бд в браузере
            Console.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}
