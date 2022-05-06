package com.fireside.pantry.db;

import com.fireside.pantry.app.Utils;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The class which handles connections to the database
 */
public class DatabaseConnector {

    private final DBConfig config;
    private Connection conn;

    /**
     * Constructor which sets the config to MySQL
     */
    public DatabaseConnector() {
        this.config = Utils.loadDBConfig("MySQL");
    }

    // -- Methods

    /**
     * Connects to database
     * @throws SQLException
     */
    public void connect() throws SQLException {
        this.conn = DriverManager.getConnection(buildUrl());
    }

    /**
     * Disconnects from database
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        this.conn.close();
        this.conn = null;
    }

    /**
     * Gets the result as a list of rows based on a query
     * @param query the query
     * @return the result
     * @throws SQLException
     */
    public List<Row> query(String query) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        List<Row> rows = parseResult(result);
        disconnect();
        return rows;
    }

    public void batchInsert(String[] queries) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        for (String query : queries) statement.addBatch(query);
        statement.executeBatch();
        disconnect();
    }

    private static List<Row> parseResult(ResultSet result) throws SQLException {
        ResultSetMetaData meta = result.getMetaData();
        LinkedList<Row> rows = new LinkedList<>();
        while(result.next()) {
            Row row = new Row();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                row.add(meta.getColumnName(i), result.getString(i));
            }
            rows.add(row);
        }
        return rows;
    }

    /**
     * Gets the images of results based on a query
     * @param query the query
     * @return the images of results
     * @throws SQLException
     */
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

    /**
     * adds image based on recipe id and input stream
     * @param recipeId the recipe id
     * @param inputStream the input stream
     * @throws SQLException
     */
    public void addImage(int recipeId, InputStream inputStream) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO RecipeImages(recipe_id, image) VALUES(?, ?);");
        statement.setInt(1, recipeId);
        statement.setBinaryStream(2, inputStream);
        statement.executeUpdate();
    }

    /**
     * Creates an url based on connection and login information
     * @return the url
     */
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

    // -- Getters

    public Connection getConnection() {
        return this.conn;
    }
}
