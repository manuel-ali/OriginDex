package com.origindex.testgame.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_PATH = "jdbc:sqlite:assets/database/origindex.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(DB_PATH);
        }
        return connection;
    }

    public static void closeCoonnection() throws SQLException{
        if (connection != null){
            connection.close();
        }
    }
}
