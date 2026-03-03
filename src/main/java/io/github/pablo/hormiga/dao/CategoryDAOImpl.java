/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.dao;
import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.model.Category;
import io.github.pablo.hormiga.interfaces.ICategoriesDAO;
import io.github.pablo.hormiga.validations.ValidationSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pavilion X360
 */
public class CategoryDAOImpl implements ICategoriesDAO{
    
    //METODO DE VALIDACION DE EXISTENCIA DE CATEGORIA
    //APOYO PARA VALIDACIN DE EXISTENCIA DENTRO DE SAVE(INSERTAR)
    public boolean existByCategoryName(String name){
        String sql = "SELECT COUNT(*) FROM categories WHERE UPPER(name) = UPPER(?)";
        
        try(Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    return rs.getInt(1)>0;
                }
                
            } catch (SQLException e) {
                System.out.println("Error al validad existencia de la categoria " + e.getMessage());
            }
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error crítico: Revise conexion a base de datos " + e.getMessage());
        }
        return false;
    }
    
    // Guarda una nueva categoria en la base de datos (Insertar)
    @Override
    public void save(Category category) {
        String sql = "INSERT INTO categories ( name) VALUES (?)";
        if(existByCategoryName(category.getName())){
            System.out.println("Error: Nombre de la categoria " + category.getName()+ "  ya existe");
            return;
        }
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, category.getName());
  
            
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Categoria " + category.getName()+ " guardada exotosamentte");
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico al insertar nueva categoria " + e.getMessage());
        }
        
    }

    
    //Busca una categoria especifica usando su numeo de identificacion unico
    @Override
    public Category findById(int id) {
        String sql = "SELECT id, name FROM categories WHERE id = ?";
        Category cat= null;
        try(Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id );
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    int categorryId = rs.getInt("id");
                    String name = rs.getString("name");
                    
                    cat = new Category( name);
                }
                
            } catch (SQLException e) {
                System.out.println("Error al buscar usuario " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Error Crítico : revise conexion " + e.getMessage());
        }
        return cat;
    }

    
    //Recuera la lista completa de todas las categorias registradas
    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT id, name, created_at FROM categories";
        
        try(Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("created_at");
                
                Category c =new Category(name, id, date);
                
                categoryList.add(c);
                
            }
            
        } catch (SQLException e) {
            System.out.println("Error al lista, revise conexion " + e.getMessage());
        }
        return categoryList;
    }

    
    // //Elimina una categoria de la base de datos mediante su ID
    @Override
    public void delete(int id) {
       
        String sql = "DELETE FROM categories WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            
            int afaccRows = ps.executeUpdate();
            
            if(afaccRows>0){
                System.out.println("Categoria " + id+ " eliminado correctamente");
            }else{
                System.out.println("No se encontro ninguna categoría con el ID " + id + " para eliminar");
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error al intentar eliminar la categoria " + e.getMessage());
        }
        
    }
    
    //metodo para actualizar
    
    // //Elimina una categoria de la base de datos mediante su ID

    @Override
    public void update(Category category) {
        
        
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        
        try(Connection conn = ConexionDB.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected> 0){
                System.out.println("Categoria actualizada con exito");
            }else{
                System.out.println("No se encontro la categoria con ID: " + category.getId());
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico, revise la conexion " + e.getMessage());
        }
    }
    
    
    
    
   
    
}
