package io.github.pablo.hormigaMain;

import io.github.pablo.hormiga.dao.*;
import io.github.pablo.hormiga.model.*;
import java.util.Scanner;

public class Hormiga_Finanzas {

    private static Scanner scanner = new Scanner(System.in);
    
    // Instancias de los DAOs (Los "Obreros" que hablan con Docker)
    private static UserDAOImpl userDAO = new UserDAOImpl();
    private static CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    private static TransactionDAOImpl transactionDAO = new TransactionDAOImpl();

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("      SISTEMA HORMIGA - GESTIÓN SQL       ");
            System.out.println("==========================================");
            System.out.println("1. 👤 GESTIONAR USUARIOS");
            System.out.println("2. 🏷️ GESTIONAR CATEGORÍAS");
            System.out.println("3. 💸 GESTIONAR TRANSACCIONES");
            System.out.println("0. ❌ SALIR");
            System.out.print("\nSeleccione una opción: ");

            try {
                // Aplicando tu regla de "Parse" para cambiar tipo de dato
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Por favor, ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1: menuUsers(); break;
                case 2: menuCategories(); break;
                case 3: menuTransactions(); break;
                case 0: System.out.println("Cerrando conexión y saliendo del sistema..."); break;
                default: System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        } while (option != 0);
    }

    // --- SUBMENÚ: GESTIÓN DE USUARIOS ---
    private static void menuUsers() {
        System.out.println("\n[ 👤 MENÚ USUARIOS ]");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Eliminar usuario por ID");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("Nombre: "); String name = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Password: "); String pass = scanner.nextLine();
                userDAO.save(new User(email, pass, name));
                break;
            case 2:
                System.out.println("\n--- Lista de Usuarios ---");
                userDAO.getAll().forEach(u -> 
                    System.out.println("ID: " + u.getId() + " | Nombre: " + u.getName() + " | Email: " + u.getEmail()));
                break;
            case 3:
                System.out.print("ID a eliminar: ");
                int id = Integer.parseInt(scanner.nextLine());
                userDAO.delete(id);
                break;
        }
    }

    // --- SUBMENÚ: GESTIÓN DE CATEGORÍAS ---
    private static void menuCategories() {
        System.out.println("\n[ 🏷️ MENÚ CATEGORÍAS ]");
        System.out.println("1. Crear nueva categoría");
        System.out.println("2. Listar todas las categorías");
        System.out.println("3. Eliminar categoría");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("Nombre de la categoría: ");
                String name = scanner.nextLine();
                categoryDAO.save(new Category(name));
                break;
            case 2:
                System.out.println("\n--- Lista de Categorías ---");
                categoryDAO.getAll().forEach(c -> 
                    System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName()));
                break;
            case 3:
                System.out.print("ID a eliminar: ");
                int id = Integer.parseInt(scanner.nextLine());
                categoryDAO.delete(id);
                break;
        }
    }

    // --- SUBMENÚ: GESTIÓN DE TRANSACCIONES (EL MÁS COMPLETO) ---
    private static void menuTransactions() {
        System.out.println("\n[ 💸 MENÚ TRANSACCIONES ]");
        System.out.println("1. Registrar movimiento (Ingreso/Gasto)");
        System.out.println("2. Ver historial completo");
        System.out.println("3. Filtrar por ID de Usuario");
        System.out.println("4. Actualizar descripción o monto");
        System.out.println("5. Eliminar movimiento");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1: // GUARDAR
                System.out.print("ID Usuario: "); int uid = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Cuenta: "); int aid = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Categoría (0 si no tiene): "); int cid = Integer.parseInt(scanner.nextLine());
                System.out.print("Monto: "); double amt = Double.parseDouble(scanner.nextLine());
                System.out.print("Tipo (INCOME/EXPENSE): "); String type = scanner.nextLine().toUpperCase();
                System.out.print("Descripción: "); String desc = scanner.nextLine();
                
                transactionDAO.save(new Transaction(uid, aid, cid, amt, type, desc));
                break;

            case 2: // LISTAR TODO
                System.out.println("\n--- Historial de Movimientos ---");
                transactionDAO.getAll().forEach(t -> 
                    System.out.println("ID: " + t.getId() + " | Tipo: " + t.getTransactionType() + 
                                     " | Monto: $" + t.getAmount() + " | Fecha: " + t.getTransactionDate()));
                break;

            case 3: // FILTRAR
                System.out.print("ID del usuario a consultar: ");
                int filterId = Integer.parseInt(scanner.nextLine());
                transactionDAO.getByUserId(filterId).forEach(t -> 
                    System.out.println("ID Movimiento: " + t.getId() + " | $" + t.getAmount() + " | " + t.getDescription()));
                break;

            case 4: // ACTUALIZAR
                System.out.print("ID de la transacción a EDITAR: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Nuevo Monto: "); double nAmt = Double.parseDouble(scanner.nextLine());
                System.out.print("Nueva Descripción: "); String nDesc = scanner.nextLine();
                
                // Objeto temporal para el update
                Transaction tUpdate = new Transaction();
                tUpdate.setId(idEdit);
                tUpdate.setAmount(nAmt);
                tUpdate.setDescription(nDesc);
                transactionDAO.update(tUpdate);
                break;

            case 5: // ELIMINAR
                System.out.print("ID de la transacción a BORRAR: ");
                int idDel = Integer.parseInt(scanner.nextLine());
                transactionDAO.delete(idDel);
                break;
        }
    }
}