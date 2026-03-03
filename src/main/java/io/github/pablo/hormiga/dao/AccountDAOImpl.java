package io.github.pablo.hormiga.dao;

import io.github.pablo.hormiga.config.ConexionDB;
import io.github.pablo.hormiga.interfaces.IAccountDAO;
import io.github.pablo.hormiga.model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements IAccountDAO {

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO accounts (user_id, name, balance) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, account.getUserId());
            ps.setString(2, account.getName());
            ps.setDouble(3, account.getBalance());
            
            ps.executeUpdate();
            System.out.println("Cuenta '" + account.getName() + "' guardada con éxito.");
            
        } catch (SQLException e) {
            System.err.println("Error al guardar la cuenta: " + e.getMessage());
        }
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET name = ?, balance = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, account.getName());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3, account.getId());
            
            if (ps.executeUpdate() > 0) {
                System.out.println("Cuenta ID " + account.getId() + " actualizada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cuenta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                System.out.println("Cuenta eliminada con éxito.");
            } else {
                System.out.println("No se encontró la cuenta con ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar la cuenta: " + e.getMessage());
        }
    }

    @Override
    public Account findById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getShort("balance") // Según tu regla de Parse
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la cuenta: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(new Account(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todas las cuentas: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Account> getByUserId(int userId) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Account(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getDouble("balance")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas del usuario: " + e.getMessage());
        }
        return list;
    }
}