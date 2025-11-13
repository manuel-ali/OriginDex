package com.origindex.testgame.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {
    static Connection getConnection() throws SQLException {
        return null;
    }

    static void closeConnection() throws SQLException{
        return;
    }
}
