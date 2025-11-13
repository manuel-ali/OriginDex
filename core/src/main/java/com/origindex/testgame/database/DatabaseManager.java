package com.origindex.testgame.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager implements DatabaseConnector{
    private static final String DB_PATH = "jdbc:sqlite:assets/database/origindex.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(DB_PATH);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if (connection != null){
            connection.close();
        }
    }

    public static Integer getNullableInt(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        return rs.wasNull() ? null : value;
    }
}
