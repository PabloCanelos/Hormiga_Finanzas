/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.dao;
import io.github.pablo.hormiga.interfaces.ITransactionDAO;
import io.github.pablo.hormiga.model.Transaction;
import java.util.List;
import io.github.pablo.hormiga.validations.ValidationSingleton;
import io.github.pablo.hormiga.dao.TransactionDAOImpl;
import io.github.pablo.hormiga.config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Pavilion X360
 */
public class TransactionDAOImpl implements ITransactionDAO {

    
    
    @Override
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions ("
                + "user_id, "
                + "account_id, "
                + "category_id, amount, "
                + "transaction_type, "
                + "description) "
                + "VALUES(?,?,?,?,?,?)";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, transaction.getUserId());
            ps.setInt(2, transaction.getAccountId());
            
            // Manejo de nulos para category_id (evita que el programa explote)
            if(transaction.getCategoryId() ==0){
                ps.setNull(3, java.sql.Types.INTEGER);
            }else{
                ps.setInt(3, transaction.getCategoryId());
            }
            
            ps.setDouble(4, transaction.getAmount());
            ps.setString(5, transaction.getTransactionType()); // "INCOME" o "EXPENSE"
            ps.setString(6, transaction.getDescription());
            
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                System.out.println("Transaccion guardada exitosamente");
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error al guardar transaccion " + e.getMessage());
        }
        
    
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY transaction_date DESC";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {              
                Transaction t = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("account_id"),
                        rs.getInt("category_id"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type"),
                        rs.getString("description"),
                        rs.getString("transactkion_date")
                
                );
                transactions.add(t);
                
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener transacciones del usuario  " + e.getMessage());
        }
        return transactions;
        
    }

    
    // Método para buscar transacciones de un usuario específico
    @Override
    public List<Transaction> getByUserId(int userId) {
        
        List<Transaction>transactions= new ArrayList<>();
        String sql = "SELECT * FROM transactions \n" +
                     "WHERE user_id = ? \n" +
                     "ORDER BY transaction_date DESC";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            // 1. Seteamos el ID del usuario que queremos buscar
            ps.setInt(1, userId);
            
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction(
                       rs.getInt("id"),
                    rs.getInt("user_id"),
                  rs.getInt("account_id"),
                 rs.getInt("category_id"),
                    rs.getDouble("amount"),
              rs.getString("transaction_type"),
                 rs.getString("description"),
              rs.getString("transaction_date")
                    );
                    
                    transactions.add(t);
                    
                    
                    
                }
                
            } catch (SQLException e) {
                System.out.println("Error al obtener transaccion de usuario" + userId+ " : " + e.getMessage()) ;
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico: Revise conexion a base de datos " + e.getMessage());
        }
        return transactions;
        
        
    }

    // Método para eliminar un movimiento por su ID
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            //seteamos el id del movimiento que queremos borrar
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0){
                System.out.println("Tansacción con id " + id+ " eliminado correctaente");
            }else{
                System.out.println("No se encontro ninguna transaccion con el id  "+id );
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico, revise conexion " + e.getMessage());
        }
        
    }

    
    // metodo para actualizar 
    @Override
    public void update(Transaction transaction) {
        String sql = " UPDATE transactions SET user_id =?,"
                + "account_id=?,"
                + "category_id=?,"
                + "ampunt =?,"
                + "transaction_type =?,"
                + "description =?,"
                + "WHERE id =?";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            //datos nuevos
            ps.setInt(1, transaction.getUserId());
            ps.setInt(2, transaction.getAccountId());
            
            //manejo de null para categoria id
            if(transaction.getCategoryId() == 0){
                ps.setNull(3, java.sql.Types.INTEGER);
            }else{
                ps.setInt(3, transaction.getCategoryId());
            }
            
            ps.setDouble(4, transaction.getAmount());
            ps.setString(5, transaction.getTransactionType());
            ps.setString(6, transaction.getDescription());
            
            ps.setInt(7, transaction.getId());
            
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Transacción " + transaction.getId()+ " completada con exito");
            }else{
                System.out.println("NO se encontro ninguna transacción para actualizar con el iD: " + transaction.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la transacción " + e.getMessage());
        }
        
    }
    
    
    
}
