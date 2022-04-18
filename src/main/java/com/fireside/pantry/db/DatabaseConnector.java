package com.fireside.pantry.db;

import com.fireside.pantry.util.Utils;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, InputStream> getImages(String query) throws SQLException {
        connect();

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        Map<Integer, InputStream> images = new HashMap<>();
        while (result.next()) {
            images.put(result.getInt(1), result.getBinaryStream(2));
        }
        disconnect();
        return images;
    }

    public void addImage(int recipeId, InputStream inputStream) throws SQLException {
        connect();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO recipeimages(recipe_id, image) VALUES(?, ?);");
        statement.setInt(1, recipeId);
        statement.setBinaryStream(2, inputStream);
        statement.executeUpdate();
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
