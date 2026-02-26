/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.finanzas.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import io.github.pablo.hormiga.model.Transaction;
import io.github.pablo.hormiga.config.ConexionDB;
/**
 *
 * @author Pavilion X360
 */
public class TransactionDAO {
    
    public boolean createNewTransaction(Transaction t) {
        if(t == null || t.getAmount() <=0){
            System.out.println("Error: Datos de transacción inválidos" );
        }
        String query = "INSERT INTO transacciones(monto, descripcion, fecha, tipo, usuario_id) VALUES (?,?,?,?,?)";
        
        try (Connection cn = ConexionDB.getInstancia().getConexion();
            PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setDouble(1, t.getAmount());
            ps.setString(2, t.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(t.getDate()));
            ps.setString(4, t.getType());
            ps.setInt(5, t.getUserId());
            
            // si filas afectadas es menor a cero ejecuta update
            int affectedRows = ps.executeUpdate(); 
            return affectedRows >0;
            
        } catch (Exception e) {
            System.out.println("DATABASE ERROR [crateNewTransaction]: "+ e.getMessage());
            return false;
        }
        
    }
    
    
}
