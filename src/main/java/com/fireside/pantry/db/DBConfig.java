package com.fireside.pantry.db;

/**
 * The class that configures the connection to the database based
 * on user and connection information
 */
public class DBConfig {

    public final String host;
    public final String port;
    public final String database;
    public final String user;
    public final String password;

    /**
     * Constructor which makes all variables the param values
     * @param host value for host
     * @param port value for port
     * @param database value for database
     * @param user value for user
     * @param password value for password
     */
    public DBConfig(String host, String port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Converts config to string
     * @return config as string
     */
    @Override
    public String toString() {
        return String.format("%s:%s:%s", host, port, database);
    }
}
