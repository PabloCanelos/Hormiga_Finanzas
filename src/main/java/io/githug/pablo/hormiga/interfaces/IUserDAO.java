/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.githug.pablo.hormiga.interfaces;
import io.github.pablo.hormiga.model.User;
import java.util.List;
/**
 *
 * @author Pavilion X360
 */
public interface IUserDAO {
    // El "QUÉ" vamos a hacer
    void save(User user);          // Para insertar nuevos (Constructor 3 params)
    User findById(int id);        // Para buscar existentes (Constructor 5 params)
    List<User> getAll();          // Para listas completas
    void update(User user);       // Para modificar datos
    void delete(int id);          // Para borrar
    
    // Método específico de negocio que no tienen otras entidades
    User login(String email, String password);
    
}
