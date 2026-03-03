/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.dao;

import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.model.User;
import io.github.pablo.hormiga.validations.ValidationSingleton;
import io.github.pablo.hormiga.interfaces.IUserDAO;
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

public class UserDAOImpl implements IUserDAO  {

    
    //GUARDAR
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES(?,?,?)";
        
        if(!ValidationSingleton.getInstance().validateUser(user)){
            System.out.println("Error, los datos no son válidos para la base de datos");
            return;
        }
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            
            int affectedRows = ps.executeUpdate();
            
            if(affectedRows >0){
                System.out.println("Usuario " + user.getName() + " Guardado exitósamente");
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico al insertar en la base de datos " + e.getMessage());
        }
    }

    //ENCONTRAR
    @Override
    public User findById(int id) {
        String sql = "SELECT id, username, email, password, created_at FROM users WHERE id =?";
        User userFound = null; //usuarioEncontrado
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id); // pasamos el id quequeremos buscar
            
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    int userId = rs.getInt("id");
                    String name = rs.getString("username");
                    String email = rs.getString("email");
                    String pass = rs.getString("password");
                    String date = rs.getString("created_at");
                    
                    // USAMOS EL CONSTRUCTOR DE 5 PARÁMETROS
                    // Aquí es donde "resucitas" al objeto con su ID y Fecha real
                    userFound = new User( email, pass,name, id, date);
                }
                
            } catch (SQLException e) {
                System.out.println("Error al buscar usuario " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos, revise conexion" + e.getMessage());
        }
        return userFound;
    }
    
    
    // MOSTRAR LISTA
    @Override
    public List<User> getAll() {
        List<User>userList = new ArrayList<>();
        String sql = "SELECT id, username, email, password, created_at FROM users";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String pass = rs.getString("password");
                String date = rs.getString("created_at");
                
                //creacion del objeto a traer
                
                User u = new User(email, pass,name,id, date);
                
                userList.add(u);
                
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios " + e.getMessage());
        }
        return userList;
    }

    
    
    //  ACTUALIZAR
    @Override
    public void update(User user) {
        String sql = " UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            // pasamos nuevos datos que usaremos
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            
            ps.setInt(4, user.getId());
            
            //IMPORTANTE: EL id va al final para el where
            
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Usuario con ID: " +user.getId()+  " Actualizadocon éxito");
            }else{
                System.out.println("No se encontro el usuario con el ID: " +user.getId());
            }
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar " + e.getMessage());
        }
        
    }

    @Override
    public void delete(int id) {
        String sql="DELETE FROM users WHERE id = ?";
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int deleteRows = ps.executeUpdate();
            
            if(deleteRows > 0){
                System.out.println("Exito: El usuairo con id: " + id+ " ha sido eliminado con éxito");
                
            }else{
                System.out.println("Advertencia : No se encontroningun usuario con el id: "+ id);
            }
            
        } catch (SQLException e) {
            System.out.println("Error crítico al intentar borrar; " + e.getMessage());
        }
    }

    
    // REGISTRA USUARIO
    @Override
    public User login(String email, String password) {
        //Buscamos un usuarioque coincida exactamente con  ambos campos
        String sql = "SELECT id, username, email, password, created_at FROM users WHERE email = ? AND password = ?";
        User userAuthenticated = null;
        
        try(Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    // si entro a qui e sporque las credenciales son correctas
                    
                    int id=  rs.getInt("id");
                    String name=rs.getString("username");
                    String emailDb= rs.getString("email");
                    String pass = rs.getString("password");
                    String date =rs.getString("created_at");
                            
                    
                    userAuthenticated = new User(emailDb, pass, name, id, date);
                    System.out.println("Login exitoso. Bienvenido " + userAuthenticated.getName());
                }else{
                    System.out.println("Error: Email o contraseña incorrectos. ");
                }
                
            } catch (SQLException e) {
                System.out.println("Error crítico en el proceso LOGIN " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos" + e.getMessage());
        }
        return userAuthenticated;
    }
    
    
    
}
