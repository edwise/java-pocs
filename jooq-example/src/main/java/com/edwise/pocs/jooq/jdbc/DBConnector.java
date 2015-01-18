package com.edwise.pocs.jooq.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
    private static final Logger log = LoggerFactory.getLogger(DBConnector.class);

    private static final String URL = "jdbc:h2:mem:pocjooq;" + "INIT=RUNSCRIPT FROM 'classpath:populate_db.sql'";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private static final DBConnector INSTANCE = new DBConnector();
    private static Connection connection = null;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    public Connection connection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = doConnection();
            }
        } catch (SQLException e) {
            log.error("Error al comprobar si está cerrada la conexión: {}", e);
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            log.debug("Conexión cerrada.");
        } catch (SQLException e) {
            log.error("Error al cerrar la conexión: {}", e);
        }
    }

    private Connection doConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error("Error al crear la conexión: {}", e);
            throw new RuntimeException(e);
        }
        log.debug("Conexion creada: {}", conn);

        return conn;
    }
}
