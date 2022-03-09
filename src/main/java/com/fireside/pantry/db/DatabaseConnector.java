package com.fireside.pantry.db;

import com.fireside.pantry.util.Utils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

    public List<Row> query(String query) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        ResultSetMetaData meta = result.getMetaData();
        LinkedList<Row> rows = new LinkedList<>();
        while(result.next()) {
            Row row = new Row();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                row.add(meta.getColumnName(i), result.getString(i));
            }
            rows.add(row);
        }
        disconnect();
        return rows;
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
