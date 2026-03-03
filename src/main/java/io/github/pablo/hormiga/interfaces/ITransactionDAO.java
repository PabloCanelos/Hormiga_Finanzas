/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.pablo.hormiga.interfaces;
import io.github.pablo.hormiga.model.Transaction;
import java.util.List;
/**
 *
 * @author Pavilion X360
 */
public interface ITransactionDAO {
    // Método para guardar una nueva transacción
    void save(Transaction transaction);
    
    // Método para obtener todas las transacciones
    List<Transaction> getAll();
    
    // Método para buscar transacciones de un usuario específico
    List<Transaction> getByUserId(int userId);
    
    // Método para eliminar un movimiento por su ID
    void delete(int id);
    
    // Método para actualizar (por si el usuario se equivocó en el monto o descripción)
    //objeto como parametro
    void update(Transaction transaction);
    
}
