/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.finanzas.dao;
import io.github.pablo.hormiga.config.ConexionDB; // Tu Singleton
import io.github.pablo.hormiga.model.User;      // Tu Clase/Tipo de dato
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Pavilion X360
 */
public class UserDAO {
    private Connection conn;

    public UserDAO() {
        // Aquí aplicamos tu estándar: pedimos la conexión al Singleton
        this.conn = ConexionDB.getInstancia().getConexion();
    }
    public boolean insert(User user) {
        // El comando SQL para Docker
        String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // "Mapeamos" los datos de tu clase User a los signos de interrogación
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            
            int rows = ps.executeUpdate();
            return rows > 0; // Si insertó algo, devuelve true
            
        } catch (SQLException e) {
            System.out.println("❌ Error SQL: " + e.getMessage());
            return false;
        }
    }
    
}
