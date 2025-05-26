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
public class LibroDAO {
    public void insertar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    libro.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void actualizar(Libro libro) throws SQLException {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Libro> listar() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection con = ConexionBD.getConexion(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(
                    rs.getInt("id"), 
                    rs.getString("titulo"), 
                    rs.getString("autor"), 
                    rs.getInt("anio_publicacion")));
            }
        }
        return libros;
    }
    
    public Libro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM libros WHERE id = ?";
        try (Connection con = ConexionBD.getConexion(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                        rs.getInt("id"), 
                        rs.getString("titulo"), 
                        rs.getString("autor"), 
                        rs.getInt("anio_publicacion"));
                }
            }
        }
        return null;
    }
}
