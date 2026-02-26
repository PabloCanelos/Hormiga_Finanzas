package io.github.pablo.hormiga.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3307/hormiga_finanzas";
    private static final String USER = "root";
    private static final String PASSWORD = "pablo123";

    private static Connection conexion = null;
    private static ConexionDB instancia;

    private ConexionDB() {
        // El constructor queda vacío o solo con el Class.forName
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error Driver: " + e.getMessage());
        }
    }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() throws SQLException {
        // REVISIÓN CLAVE: Si la conexión es nula o se cerró, la volvemos a abrir
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión establecida con Docker.");
        }
        return conexion;
    }
}