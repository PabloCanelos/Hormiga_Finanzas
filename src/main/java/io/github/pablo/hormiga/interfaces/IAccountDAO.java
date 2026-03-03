/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.pablo.hormiga.interfaces;
import io.github.pablo.hormiga.model.Account;
import java.util.List;
/**
 *
 * @author Pavilion X360
 */
public interface IAccountDAO {
    
    // Guarda una nueva cuenta en la base de datos (Ej: Efectivo, Banco)
    void save(Account account);

    // Actualiza los datos de una cuenta existente (como cambiar el nombre o el saldo)
    void update(Account account);

    // Elimina una cuenta de forma permanente usando su ID único
    void delete(int id);

    // Busca y devuelve una cuenta específica comparando por su ID
    Account findById(int id);

    // Recupera la lista completa de todas las cuentas registradas en el sistema
    List<Account> getAll();

    // Filtra y devuelve solo las cuentas que pertenecen a un usuario específico
    List<Account> getByUserId(int userId);
    
    
}
