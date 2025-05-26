/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.*;

/**
 *
 * @author miguel.escudero
 */
public class LectorDAO {
    public void insertar(Lector lector) throws SQLException {
        String sql = "INSERT INTO lectores (nombre, email) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, lector.getNombre());
            stmt.setString(2, lector.getEmail());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lector.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void actualizar(Lector lector) throws SQLException {
        String sql = "UPDATE lectores SET nombre = ?, email = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, lector.getNombre());
            stmt.setString(2, lector.getEmail());
            stmt.setInt(3, lector.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM lectores WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Lector> listar() throws SQLException {
        List<Lector> lectores = new ArrayList<>();
        String sql = "SELECT * FROM lectores";
        try (Connection con = ConexionBD.getConexion(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lectores.add(new Lector(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("email")));
            }
        }
        return lectores;
    }
    
    public Lector buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM lectores WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Lector(
                        rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("email"));
                }
            }
        }
        return null;
    }
}
