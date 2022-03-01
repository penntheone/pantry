package com.fireside.pantry.db;

public class DBConfig {

    public final String host;
    public final String port;
    public final String database;
    public final String user;
    public final String password;

    public DBConfig(String host, String port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", host, port, database);
    }
}
