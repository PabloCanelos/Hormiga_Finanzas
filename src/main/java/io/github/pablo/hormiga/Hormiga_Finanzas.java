/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.pablo.hormiga;
import io.github.pablo.finanzas.dao.UserDAO;
import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.model.User;
/**
 *
 * @author Pavilion X360
 */
public class Hormiga_Finanzas {

    public static void main(String[] args) {
        //ConexionDB.getInstancia().getConexion();
        System.out.println("Iniciando prueba de persistencia");
        User testUser = new User(0," pabloAnalista", " emaail@", "admin123");
        UserDAO userDAO= new UserDAO();
        
        if(userDAO.insert(testUser)){
            System.out.println("Exito: El usuario ha sido guardado en el contenedor docker");
        }else{
            System.out.println("ERROR: no se pudo guardar el usuario. Revisa la consola");
        }
    }
}
