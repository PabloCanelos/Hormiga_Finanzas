/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.pablo.hormiga.interfaces;
import io.github.pablo.hormiga.model.Category;
import java.util.List;
/**
 *
 * @author Pavilion X360
 */
public interface ICategoriesDAO {
    
    // Guardauna nueva categoria en la base de datos (Insertar)
    void  save(Category category);
    
    //Busca una categoria especifica usando su numeo de identificacion unico
    Category findById(int id);
    
    //Recuera la lista completa de todas las categorias registradas
    List<Category> getAll();
    
    //Elimina una categoria de la base de datos mediante su ID
    
    void delete(int id);

    // metodo para modificar
    void update(Category category);
}
