package io.github.pablo.hormigaMain;

import io.github.pablo.hormiga.dao.*;
import io.github.pablo.hormiga.interfaces.*;
import io.github.pablo.hormiga.model.*;
import java.util.List;
import java.util.Scanner;

public class Hormiga_Finanzas {

    private static Scanner scanner = new Scanner(System.in);
    
    // Instancias de los DAOs usando sus Interfaces (Estándar Profesional)
    private static IUserDAO userDAO = new UserDAOImpl();
    private static ICategoriesDAO categoryDAO = new CategoryDAOImpl();
    private static IAccountDAO accountDAO = new AccountDAOImpl();
    private static ITransactionDAO transactionDAO = new TransactionDAOImpl();

    public static void main(String[] args) {
        int option = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("      SISTEMA HORMIGA - GESTIÓN TOTAL     ");
            System.out.println("==========================================");
            System.out.println("1. 👤 GESTIONAR USUARIOS");
            System.out.println("2. 🏷️ GESTIONAR CATEGORÍAS");
            System.out.println("3. 🏦 GESTIONAR CUENTAS");
            System.out.println("4. 💸 GESTIONAR TRANSACCIONES");
            System.out.println("0. ❌ SALIR");
            System.out.print("\nSeleccione una opción: ");

            try {
                // Usando Parse para cambiar el tipo de dato de la entrada
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1: menuUsers(); break;
                case 2: menuCategories(); break;
                case 3: menuAccounts(); break;
                case 4: menuTransactions(); break;
                case 0: System.out.println("Finalizando sistema... ¡Adiós!"); break;
                default: System.out.println("❌ Opción no válida.");
            }
        } while (option != 0);
    }

    // --- MENÚ CUENTAS (Lo nuevo que faltaba) ---
    private static void menuAccounts() {
        System.out.println("\n[ 🏦 MENÚ CUENTAS ]");
        System.out.println("1. Crear nueva cuenta");
        System.out.println("2. Listar mis cuentas (por User ID)");
        System.out.println("3. Eliminar cuenta");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("ID Usuario dueño: "); int uid = Integer.parseInt(scanner.nextLine());
                System.out.print("Nombre (Ej: Ahorros): "); String name = scanner.nextLine();
                System.out.print("Saldo inicial: "); double balance = Double.parseDouble(scanner.nextLine());
                accountDAO.save(new Account(uid, name, balance));
                break;
            case 2:
                System.out.print("Ingrese su ID de Usuario: "); int filterId = Integer.parseInt(scanner.nextLine());
                accountDAO.getByUserId(filterId).forEach(a -> 
                    System.out.println("ID: " + a.getId() + " | Nombre: " + a.getName() + " | Saldo: $" + a.getBalance()));
                break;
            case 3:
                System.out.print("ID de la cuenta a eliminar: "); int id = Integer.parseInt(scanner.nextLine());
                accountDAO.delete(id);
                break;
        }
    }

    // --- MENÚ TRANSACCIONES ---
    private static void menuTransactions() {
        System.out.println("\n[ 💸 MENÚ TRANSACCIONES ]");
        System.out.println("1. Registrar movimiento");
        System.out.println("2. Ver historial de un usuario");
        System.out.println("3. Eliminar movimiento");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1:
                System.out.print("ID Usuario: "); int uid = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Cuenta: "); int aid = Integer.parseInt(scanner.nextLine());
                System.out.print("ID Categoría: "); int cid = Integer.parseInt(scanner.nextLine());
                System.out.print("Monto: "); double amt = Double.parseDouble(scanner.nextLine());
                System.out.print("Tipo (INCOME/EXPENSE): "); String type = scanner.nextLine();
                System.out.print("Descripción: "); String desc = scanner.nextLine();
                transactionDAO.save(new Transaction(uid, aid, cid, amt, type, desc));
                break;
            case 2:
                System.out.print("ID Usuario para historial: "); 
                int filter = Integer.parseInt(scanner.nextLine());

                List<Transaction> list = transactionDAO.getByUserId(filter);

                if (list.isEmpty()) {
                    System.out.println("📭 No se encontraron transacciones para el usuario " + filter);
                } else {
                    System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
                    list.forEach(t -> 
                        System.out.println("ID: " + t.getId() + " | Tipo: " + t.getTransactionType() + 
                                           " | Monto: $" + t.getAmount() + " | Desc: " + t.getDescription()));
                }

                // ESTA ES LA CLAVE: Una pausa para que no se borre la pantalla
                System.out.println("\nPresione ENTER para volver al menú...");
                scanner.nextLine(); 
                break;
            case 3:
                System.out.print("ID a eliminar: "); int idDel = Integer.parseInt(scanner.nextLine());
                transactionDAO.delete(idDel);
                break;
        }
    }

    // --- MENÚ USUARIOS ---
    private static void menuUsers() {
        System.out.println("\n[ 👤 MENÚ USUARIOS ]");
        System.out.println("1. Registrar");
        System.out.println("2. Listar todos");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());
        if(opt == 1) {
            System.out.print("Nombre: "); String n = scanner.nextLine();
            System.out.print("Email: "); String e = scanner.nextLine();
            System.out.print("Pass: "); String p = scanner.nextLine();
            userDAO.save(new User(e, p, n));
        } else {
            userDAO.getAll().forEach(u -> System.out.println(u.getId() + ": " + u.getName()));
        }
    }

    // --- MENÚ CATEGORÍAS ---
    private static void menuCategories() {
        System.out.println("\n[ 🏷️ MENÚ CATEGORÍAS ]");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.print("Selección: ");
        int opt = Integer.parseInt(scanner.nextLine());
        if(opt == 1) {
            System.out.print("Nombre: "); String n = scanner.nextLine();
            categoryDAO.save(new Category(n));
        } else {
            categoryDAO.getAll().forEach(c -> System.out.println(c.getId() + ": " + c.getName()));
        }
    }
}