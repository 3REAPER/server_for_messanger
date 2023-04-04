package com.pervukhin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class LibraryApp {
    private static final String CON_STR = "jdbc:sqlite:C:Users/Nikita/Desktop/database.db";
    public static Connection connection;


    public static void main(String[] args){
        //Запуск SpringBoot-приложения и получение контекста
        try {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(CON_STR);
        }catch (Exception e){
            e.printStackTrace();
            connection = null;
        }

        ConfigurableApplicationContext context = SpringApplication.run(LibraryApp.class, args);

    }
}
