/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.pablo.hormigaMain;
import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.dao.CategoryDAOImpl;
import io.github.pablo.hormiga.dao.UserDAOImpl;
import io.github.pablo.hormiga.model.User;
import java.util.Scanner;
import io.github.pablo.hormiga.validations.ValidationSingleton;
import io.github.pablo.hormiga.interfaces.IUserDAO;
import io.github.pablo.hormiga.model.Category;
import java.util.List;

/**
 *
 * @author Pavilion X360
 */
public class Hormiga_Finanzas {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAOImpl userDAO = new UserDAOImpl();
    private static CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("       SISTEMA HORMIGA - GESTIÓN SQL      ");
            System.out.println("==========================================");
            System.out.println("1. 👤 GESTIONAR USUARIOS");
            System.out.println("2. 🏷️ GESTIONAR CATEGORÍAS");
            System.out.println("0. ❌ SALIR");
            System.out.print("\nSeleccione una opción: ");
            
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1: menuUsers(); break;
                case 2: menuCategories(); break;
                case 0: System.out.println("Cerrando conexión y saliendo..."); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (option != 0);
    }

    // --- CRUD DE USUARIOS ---
    private static void menuUsers() {
        System.out.println("\n[ 👤 MENÚ USUARIOS ]");
        System.out.println("1. Registrar nuevo");
        System.out.println("2. Listar todos");
        System.out.println("3. Buscar por ID");
        System.out.println("4. ELIMINAR USUARIO");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("Username: "); String user = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Password: "); String pass = scanner.nextLine();
                userDAO.save(new User(email, pass, user));
                break;
            case 2:
                System.out.println("\n--- Lista de Usuarios en DB ---");
                userDAO.getAll().forEach(u -> System.out.println("ID: " + u.getId() + " | User: " + u.getName()+ " | Email: " + u.getEmail()));
                break;
            case 3:
                System.out.print("ID a buscar: ");
                int idB = Integer.parseInt(scanner.nextLine());
                User found = userDAO.findById(idB);
                if(found != null) System.out.println("Encontrado: " + found.getName());
                break;
            case 4:
                System.out.print("ID del usuario a ELIMINAR: ");
                int idE = Integer.parseInt(scanner.nextLine());
                userDAO.delete(idE); // Asegúrate de tener el método delete en UserDAOImpl
                break;
        }
    }

    // --- CRUD DE CATEGORÍAS ---
    private static void menuCategories() {
        System.out.println("\n[ 🏷️ MENÚ CATEGORÍAS ]");
        System.out.println("1. Crear categoría");
        System.out.println("2. Listar todas");
        System.out.println("3. Buscar por ID");
        System.out.println("4. ELIMINAR CATEGORÍA");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("Nombre de categoría: ");
                String name = scanner.nextLine();
                categoryDAO.save(new Category(name));
                break;
            case 2:
                System.out.println("\n--- Lista de Categorías en DB ---");
                categoryDAO.getAll().forEach(c -> System.out.println("ID: " + c.getId() + " | Name: " + c.getName() + " | Creado: " + c.getCreated_At()));
                break;
            case 3:
                System.out.print("ID a buscar: ");
                int idB = Integer.parseInt(scanner.nextLine());
                Category found = categoryDAO.findById(idB);
                if(found != null) System.out.println("Encontrada: " + found.getName());
                break;
            case 4:
                System.out.print("ID de categoría a ELIMINAR: ");
                int id = Integer.parseInt(scanner.nextLine());
                categoryDAO.delete(id);
                break;
        }
    }
   
    


    
}
