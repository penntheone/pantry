package com.fireside.pantry.db;

import com.fireside.pantry.util.Utils;

import java.sql.*;

public class DatabaseConnector {

    private final DBConfig config;
    private Connection conn;

    public DatabaseConnector() {
        this.config = Utils.loadDBConfig("MySQL");
    }

    private void connect() throws SQLException {
        this.conn = DriverManager.getConnection(buildUrl());
    }

    private void disconnect() throws SQLException {
        this.conn.close();
        this.conn = null;
    }

    public void query(String query) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        ResultSetMetaData meta = result.getMetaData();

        while(result.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%s : %s\t\t", meta.getColumnName(i), result.getString(i));
            }
            System.out.println();
        }

        disconnect();
    }

    public String buildUrl() {
        return String.format(
                "jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                config.host,
                config.port,
                config.database,
                config.user,
                config.password
        );
    }
}
