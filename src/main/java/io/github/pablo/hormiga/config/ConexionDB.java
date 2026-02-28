package io.github.pablo.hormiga.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // La instancia única (Singleton)
    private static ConexionDB instancia;
    private Connection connection;
    
    // Configuración para tu Docker
    // Nota: Usamos 3307 porque es el puerto que me mostraste en tu log
    private final String URL = "jdbc:mysql://localhost:3307/hormiga_db";
    private final String USER = "root";
    private final String PASS = "pablo123"; // Si no le pusiste pass en el docker, déjalo vacío

    // Constructor privado para el Singleton
    private ConexionDB() {
        try {
            // Asegúrate de tener el driver de MySQL en tu proyecto
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Singleton: Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    // El método que entrega la conexión
    public static Connection getConexion() {
        try {
            if(instancia == null){
                instancia = new ConexionDB();
            }
            // Si no existe o se cerró (por Docker o timeout), creamos una nueva
            if (instancia == null || instancia.connection == null || instancia.connection.isClosed()) {
                instancia = new ConexionDB();
        }
        } catch (SQLException e) {
            System.out.println("❌ Error al verificar la conexión: " + e.getMessage());
        }
        return instancia.connection;
    }
}