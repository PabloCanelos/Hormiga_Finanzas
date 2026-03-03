package io.github.pablo.hormiga.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static ConexionDB instancia;
    private Connection connection;
    
    private final String URL = "jdbc:mysql://localhost:3307/hormiga_db";
    private final String USER = "root";
    private final String PASS = "pablo123";

    // Constructor privado
    private ConexionDB() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Singleton: Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    // 1. Este es el método que te faltaba (El que pide el Singleton)
    public static synchronized ConexionDB getInstance() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    // 2. Este es el que entrega la conexión (El que usas en el DAO)
    public Connection getConnection() {
        try {
            // Si por alguna razón Docker cerró la conexión, la reabrimos
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar conexión: " + e.getMessage());
        }
        return connection;
    }
}