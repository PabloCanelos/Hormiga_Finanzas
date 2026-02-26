

package io.github.pablo.hormiga.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Datos de tu Docker (lo que configuramos en Workbench)
    private static final String URL = "jdbc:mysql://localhost:3307/hormiga_finanzas";
    private static final String USER = "root";
    private static final String PASSWORD = "pablo123";

    private static Connection conexion = null;
    private static ConexionDB instancia;

    private ConexionDB() {
        try {
            // Este es el driver que acabas de bajar en el POM
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa al contenedor Docker");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}