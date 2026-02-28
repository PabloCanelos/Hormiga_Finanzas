/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.pablo.hormigaMain;
import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.dao.UserDAOImpl;
import io.github.pablo.hormiga.model.User;
import java.util.Scanner;
import io.github.pablo.hormiga.validations.ValidationSingleton;
import io.githug.pablo.hormiga.interfaces.IUserDAO;
import java.util.List;

/**
 *
 * @author Pavilion X360
 */
public class Hormiga_Finanzas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    // 1. Instanciamos el DAO (Usando la interfaz como contrato)
    IUserDAO dao = new UserDAOImpl();
    
        System.out.println("--- 🐜 TEST DE CONEXIÓN HORMIGA ---");
        System.out.println("1. Probar INSERT (Guardar)");
        System.out.println("2. Probar SELECT (Buscar por ID)");
        System.out.println("3)");
        System.out.println("4.Probar listar");
        System.out.print("Elije (1 o 2): ");
        int test = sc.nextInt();
        sc.nextLine(); // Limpieza de buffer

        if (test == 1) {
            // --- PRUEBA DE INSERT ---
            System.out.print("Nombre: "); String n = sc.nextLine();
            System.out.print("Email: ");  String e = sc.nextLine();
            System.out.print("Pass: ");   String p = sc.nextLine();

            // Creamos el modelo (Constructor de 3 parámetros)
            User u = new User(n, e, p);
            dao.save(u); // Aquí se activa el Singleton de Validación y la Conexión

        } else if (test == 2) {
            // --- PRUEBA DE SELECT ---
            System.out.print("ID a buscar en Docker: ");
            int idBuscado = sc.nextInt();

            User resultado = dao.findById(idBuscado);

            if (resultado != null) {
                System.out.println("✅ ¡ENCONTRADO!");
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Email: " + resultado.getEmail());
                System.out.println("Fecha en DB: " + resultado.getCreated_At());
            } else {
                System.out.println("❌ No existe el usuario con ID: " + idBuscado);
            }
            
            }
        else if(test == 4){
            System.out.println("\n--- 📋 LISTA DE USUARIOS EN DOCKER ---");
    List<User> todos = dao.getAll();
    
    if (todos.isEmpty()) {
        System.out.println("La base de datos está vacía 📭");
    } else {
        // Usamos un for-each profesional para recorrer la lista
        for (User u : todos) {
            System.out.println("ID: " + u.getId() + 
                               " | Nombre: " + u.getName() + 
                               " | Email: " + u.getEmail() +
                               " | Creado: " + u.getCreated_At()); // Tu getter corregido
        }
    }
            
            
        }
}
}